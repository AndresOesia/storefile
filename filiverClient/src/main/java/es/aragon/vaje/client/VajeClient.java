package es.aragon.vaje.client;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import es.aragon.vaje.bean.ParamChunk;
import es.aragon.vaje.bean.ResultChunkReady;

public class VajeClient {

	Logger logger = Logger.getAnonymousLogger();

	File file;
	String nif;
	String app;
	String filename;
	String fileType;
	String url;

	public VajeClient(File file, String nif, String app, String filename, String fileType, String url) {
		super();
		this.file = file;
		this.nif = nif;
		this.app = app;
		this.filename = filename;
		this.fileType = fileType;
		this.url = url;
	}

	public void executeStore() throws IOException {

		byte[] bytes = FileUtils.readFileToByteArray(file);
		logger.info("Tamaño " + bytes.length);

		WebClient client = WebClient.create(url, Arrays.asList(new JacksonJsonProvider()));

		Response r = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get();

		ResultChunkReady rcr = r.readEntity(ResultChunkReady.class);

		logger.info("Host : " + rcr.getHost());
		logger.info("Destiny : " + rcr.getDestiny());
		logger.info("Seed " + rcr.getSeed());
		logger.info("Next Chunk " + rcr.getNextChunk());

		// Montar correctamente el path
		WebClient clientStoreFile = WebClient.create(rcr.getHost(), Arrays.asList(new JacksonJsonProvider()));

		int bufferSize = 1024 * 1024 * 1;
		int currentPosition = 0;
		byte[] currentChunk = new byte[bufferSize];

		ParamChunk fileChunk = new ParamChunk();
		fileChunk.setSeed(rcr.getSeed());
		// Esto va a ser común a todas las llamadas
		fileChunk.setFileName(filename);
		fileChunk.setMimeType(fileType);
		fileChunk.setNif(nif);
		fileChunk.setApp(app);

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
			logger.info("Tamaño del current :" + currentChunk.length + " Y tamaño del buffer " + bufferSize);
			String chunkToSend = Base64.encode(currentChunk);
			fileChunk.setChunk(chunkToSend);
			fileChunk.setFileChunk(chunkNumber++);

			Response r5 = clientStoreFile.accept("application/json").type("application/json").post(fileChunk);
			logger.info(String.valueOf(r5.getStatus()));
			ResultChunkReady rcrIteration = r5.readEntity(ResultChunkReady.class);
			logger.info(rcrIteration.getDestiny());
		}

	}

}
