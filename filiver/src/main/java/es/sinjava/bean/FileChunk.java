package es.sinjava.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileChunk {
	
	private int number;
	private int chunkNumber;
	private String chunk;
	private String id;
	private String path;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getChunkNumber() {
		return chunkNumber;
	}

	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}

}
