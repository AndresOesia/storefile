package es.sinjava;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import es.sinjava.bean.FileChunk;
import es.sinjava.bean.FileChunkInfo;
import es.sinjava.bean.FilePreparedChunks;

@Path("/hello")
public class HelloWorld {

	@GET
	@Path("/echo/{input}")
	@Produces("text/plain")
	public String ping(@PathParam("input") String input) {
		return input;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/jsonBean")
	public Response modifyJson(JsonBean input) {
		input.setVal2(input.getVal1());
		return Response.ok().entity(input).build();
	}

	@GET
	@Path("/file/{input}")
	@Produces({ "application/xml", "application/json" })

	public Response uploadFile(@PathParam("input") String inputName) {
		FileChunkInfo fileChunkInfo = new FileChunkInfo();
		fileChunkInfo.setAll(15);
		fileChunkInfo.setChunk(6);
		fileChunkInfo.setRetrieved(true);
		fileChunkInfo.setFilename(inputName);
		return Response.ok().entity(fileChunkInfo).build();
	}

	@POST
	@Path("/filein")
	@Produces({ "application/xml", "application/json" })
	@Consumes("application/json")
	public Response uploadChunkFile(FileChunk fileChunk) {
		FileChunkInfo fileChunkInfo = new FileChunkInfo();

		try {
			File tempFile = new File(fileChunk.getPath());
			byte[] contenido = Base64.decode(fileChunk.getChunk());
			FileUtils.writeByteArrayToFile(tempFile, contenido, true);
			if (fileChunk.getChunkNumber() == fileChunk.getNumber()) {
				File destiny = new File(fileChunk.getId());
				System.out.println(destiny.getAbsolutePath());
				FileUtils.moveFile(tempFile, destiny);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileChunkInfo.setAll(15);
		fileChunkInfo.setChunk(fileChunk.getNumber());
		fileChunkInfo.setRetrieved(true);
		fileChunkInfo.setFilename(fileChunk.getId());
		return Response.ok().entity(fileChunkInfo).build();
	}

	@POST
	@Path("/fileprep")
	@Produces({ "application/xml", "application/json" })
	@Consumes("application/json")
	public Response prepareChunks(FilePreparedChunks fileChunk) {

		try {
			File tempFile = File.createTempFile("guay", ".tmp");
			fileChunk.setSeed(tempFile.getPath());
		} catch (IOException e) {
			// DO Nothing
		}
		fileChunk.setHost("http://localhost:8080/filiver/hello/filein");
		fileChunk.setChunkLength(2500);
		return Response.ok().entity(fileChunk).build();
	}

}
