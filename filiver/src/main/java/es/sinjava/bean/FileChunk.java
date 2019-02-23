package es.sinjava.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileChunk {
	private int number;
	private String chunk;
	private String id;

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

}
