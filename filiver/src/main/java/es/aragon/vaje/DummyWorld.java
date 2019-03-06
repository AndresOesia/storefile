package es.aragon.vaje;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import es.aragon.vaje.bean.ParamChunk;
import es.aragon.vaje.bean.ResultChunkReady;

@Path("/rest/fileManager")
public class DummyWorld {
	Logger log = Logger.getAnonymousLogger();

	@GET
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	@Path("/{applicationId}/{instanceId}/{user}")
	public Response prepare(@PathParam("applicationId") String appId, @PathParam("instanceId") Integer instanceId,
			@PathParam("user") String user) throws IOException {
		// Validación ad hoc de los parámetros
		InetAddress host = InetAddress.getLocalHost();
		// algo debería controlar el puerto para hacerlo más automatico
		String path = "/upload";
		ResultChunkReady chunkReady = new ResultChunkReady();
		// En el puerto por defecto
		chunkReady.setHost("http://localhost:8080/dummy/rest/fileManager/upload");
		chunkReady.setDestiny(path);
		chunkReady.setSeed(File.createTempFile("vaje", ".tmp").getAbsolutePath());
		chunkReady.setNextChunk(1);
		// Controlar el pete
		return Response.ok().entity(chunkReady).build();
	}

	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	@Path("/upload")
	public Response upload(ParamChunk paramChunk) throws IOException {
		log.info("Se recibe el parámetro correcto  " + paramChunk.getFileName());
		File currentFile = new File(paramChunk.getSeed());
		// Wow, utilizando una clase de openCmis. Problema si se cambian las librerias
		byte[] contenido = Base64.decode(paramChunk.getChunk());
		log.info("Paso el trozo " + paramChunk.getFileChunk());

		OutputStream outputStream = new FileOutputStream(currentFile, true);
		outputStream.write(contenido);
		outputStream.close();
		// hemos llegado al final
		ResultChunkReady chunkReady = new ResultChunkReady();
		if (paramChunk.getAmountChunk() == paramChunk.getFileChunk()) {
			try {

				File destiny = new File(System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis()
						+ paramChunk.getFileName());
				FileUtils.copyFile(currentFile, destiny);

				log.info(" Se envia documento del nif " + paramChunk.getNif());

			} catch (IOException ioe) {
				log.severe("Problemas al mover el archivo" + ioe.getStackTrace());
			}
		} else {
			chunkReady.setResultCreateDocument("Uploading");
		}

		chunkReady.setHost("host");
		chunkReady.setDestiny("path");
		chunkReady.setNextChunk(paramChunk.getFileChunk() + 1);

		return Response.ok().entity(chunkReady).build();
	}
}
