package es.sinjava;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import es.sinjava.bean.ParamChunk;
import es.sinjava.bean.ResultChunkReady;

/**
 * Hello world!
 *
 */
public class Concatenate {
	public static void main(String[] args) throws IOException {

		File sharleen = new File(Concatenate.class.getClassLoader().getResource("sharleen.jpg").getFile());
		byte[] bytes = FileUtils.readFileToByteArray(sharleen);
		System.out.println("Tamañoo " + bytes.length);
		String chunk = Base64.encode(bytes);
		System.out.println(" En base 64 " + chunk);

		WebClient client = WebClient.create("http://localhost:7001/vaje_core/rest/fileManager/VAJE/1/11111111H",
				Arrays.asList(new JacksonJsonProvider()));
		Response r = client.accept("application/json").type("application/json").get();

		System.out.println(r.getStatus());

		ResultChunkReady rcr = r.readEntity(ResultChunkReady.class);

		System.out.println("Host : " + rcr.getHost());
		System.out.println("Destiny : " + rcr.getDestiny());
		System.out.println("Seed " + rcr.getSeed());
		System.out.println("Next Chunk " + rcr.getNextChunk());

		// Montar correctamente el path
		WebClient clientStoreFile = WebClient.create(rcr.getHost(), Arrays.asList(new JacksonJsonProvider()));

		int bufferSize = 1024 * 1;
		int currentPosition = 0;
		byte[] currentChunk = new byte[bufferSize];

		ParamChunk fileChunk = new ParamChunk();
		fileChunk.setSeed(rcr.getSeed());
		// Esto va a ser común a todas las llamadas
		fileChunk.setFileName("sharleen.jpg");
		fileChunk.setMimeType("image/jpg");
		fileChunk.setNif("11111111T");
		fileChunk.setApp("VIAJE");
		fileChunk.setFileSize(bytes.length);
		int amount = (bytes.length / bufferSize) + 1;
		fileChunk.setAmountChunk(amount);

		// no lo ha indicado el servicio
		int chunkNumber = rcr.getNextChunk();

		while (currentPosition < bytes.length) {
			if (currentPosition + bufferSize > bytes.length) {
				currentChunk = new byte[bytes.length - currentPosition];
			}
			System.arraycopy(bytes, currentPosition, currentChunk, 0, currentChunk.length);
			currentPosition += bufferSize;
			System.out.println("Tamaño del current :" + currentChunk.length + " Y tamaño del buffer " + bufferSize);
			String chunkToSend = Base64.encode(currentChunk);
			fileChunk.setChunk(chunkToSend);
			fileChunk.setFileChunk(chunkNumber++);

			Response r5 = clientStoreFile.accept("application/json").type("application/json").post(fileChunk);
			System.out.println(r5.getStatus());
			ResultChunkReady rcrIteration = r5.readEntity(ResultChunkReady.class);
			System.out.println(rcrIteration.getDestiny());
		}

	}
}
