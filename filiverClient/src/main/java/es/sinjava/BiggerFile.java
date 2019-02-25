package es.sinjava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.MappingJsonFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import es.sinjava.bean.FileChunk;
import es.sinjava.bean.FileChunkInfo;
import es.sinjava.bean.FilePreparedChunks;

/**
 * Hello world!
 *
 */
public class BiggerFile {

	public static void main(String[] args) throws IOException {

		File sharleen = new File(BiggerFile.class.getClassLoader().getResource("MAIN.7z").getFile());
		byte[] bytes = FileUtils.readFileToByteArray(sharleen);

		WebClient client = WebClient.create("http://localhost:8080" + "/filiver/hello/file/Andres",
				Arrays.asList(new JacksonJsonProvider()));
		Response r = client.accept("application/json").type("application/json").get();

		System.out.println(r.getStatus());

		MappingJsonFactory factory = new MappingJsonFactory();
		JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
		FileChunkInfo output = parser.readValueAs(FileChunkInfo.class);
		System.out.println(output.getFilename());

		// Creamos el contenedor en el servidor

		WebClient client3 = WebClient.create("http://localhost:8080" + "/filiver/hello/fileprep",
				Arrays.asList(new JacksonJsonProvider()));

		FilePreparedChunks filePreparedChunks = new FilePreparedChunks();
		filePreparedChunks.setChunkLength(1500);

		Response r3 = client3.accept("application/json").type("application/json").post(filePreparedChunks);
		System.out.println("Respuesta " + r3.getStatus());

		filePreparedChunks = r3.readEntity(FilePreparedChunks.class);

		// ya tenemos las url de destino
		System.out.println(filePreparedChunks.getHost());
		System.out.println(filePreparedChunks.getSeed());
		System.out.println(filePreparedChunks.getChunkLength());

		// Sobre los datos recogidos lanzo el store document

		WebClient clientStoreFile = WebClient.create(filePreparedChunks.getHost(),
				Arrays.asList(new JacksonJsonProvider()));

		int bufferSize = 1024 * 1024 * 50;
		int currentPosition = 0;
		byte[] currentChunk = new byte[bufferSize];

		FileChunk fileChunk = new FileChunk();
		fileChunk.setPath(filePreparedChunks.getSeed());
		fileChunk.setId("MAIN.7z");
		int chunkNumber = 0;

		while (currentPosition < bytes.length) {
			if (currentPosition + bufferSize > bytes.length) {
				currentChunk = new byte[bytes.length - currentPosition];
			}
			System.arraycopy(bytes, currentPosition, currentChunk, 0, currentChunk.length);
			currentPosition += bufferSize;
			System.out.println("Tamaño del current " + currentChunk.length);
			String chunk = Base64.encode(currentChunk);
			fileChunk.setChunk(chunk);
			fileChunk.setNumber(chunkNumber++);
			Response r5 = clientStoreFile.accept("application/json").type("application/json").post(fileChunk);
			System.out.println(r5.getStatus());
		}

	}
}
