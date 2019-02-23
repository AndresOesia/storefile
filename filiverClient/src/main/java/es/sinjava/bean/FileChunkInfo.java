package es.sinjava.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileChunkInfo {

	private String filename;
	private int chunk;
	private int all;
	private boolean retrieved;

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public boolean isRetrieved() {
		return retrieved;
	}

	public void setRetrieved(boolean retrieved) {
		this.retrieved = retrieved;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
