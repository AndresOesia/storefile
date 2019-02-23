package es.sinjava;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import es.sinjava.bean.FileChunk;
import es.sinjava.bean.FileChunkInfo;

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
		fileChunkInfo.setAll(15);
		fileChunkInfo.setChunk(6);
		fileChunkInfo.setRetrieved(true);
		fileChunkInfo.setFilename(fileChunk.getId());
		return Response.ok().entity(fileChunkInfo).build();
	}
}
