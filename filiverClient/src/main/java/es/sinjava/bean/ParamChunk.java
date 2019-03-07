package es.sinjava.bean;

/**
 * ï»¿Copyright (C) 2019 Gobierno de Aragón
 * License: EUPL (version 1.1 or later). Ver LICENSE.txt Para los detalles.
 */

/**
 * @author agaudioso
 * 
 */
public class ParamChunk {

	private String app;
	private String nif;
	private String fileName;
	private String mimeType;
	private long fileSize;
	private int fileChunk;
	private int amountChunk;
	private String chunk;
	private String seed;

	/**
	 * Método que obtiene el valor de la propiedad app
	 * 
	 * @return valor de la propiedad app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * Método que establece el valor de la propiedad app
	 * 
	 * @param app,
	 *            valor que se estable para la propiedad app
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Método que obtiene el valor de la propiedad nif
	 * 
	 * @return valor de la propiedad nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Método que establece el valor de la propiedad nif
	 * 
	 * @param nif,
	 *            valor que se estable para la propiedad nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Método que obtiene el valor de la propiedad fileName
	 * 
	 * @return valor de la propiedad fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Método que establece el valor de la propiedad fileName
	 * 
	 * @param fileName,
	 *            valor que se estable para la propiedad fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Método que obtiene el valor de la propiedad mimeType
	 * 
	 * @return valor de la propiedad mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Método que establece el valor de la propiedad mimeType
	 * 
	 * @param mimeType,
	 *            valor que se estable para la propiedad mimeType
	 */
	public void setMimeType(String fileType) {
		this.mimeType = fileType;
	}

	/**
	 * Método que obtiene el valor de la propiedad fileSize
	 * 
	 * @return valor de la propiedad fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * Método que establece el valor de la propiedad fileSize
	 * 
	 * @param fileSize,
	 *            valor que se estable para la propiedad fileSize
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Método que obtiene el valor de la propiedad fileChunk
	 * 
	 * @return valor de la propiedad fileChunk
	 */
	public int getFileChunk() {
		return fileChunk;
	}

	/**
	 * Método que establece el valor de la propiedad fileChunk
	 * 
	 * @param fileChunk,
	 *            valor que se estable para la propiedad fileChunk
	 */
	public void setFileChunk(int fileChunk) {
		this.fileChunk = fileChunk;
	}

	/**
	 * Método que obtiene el valor de la propiedad amountChunk
	 * 
	 * @return valor de la propiedad amountChunk
	 */
	public int getAmountChunk() {
		return amountChunk;
	}

	/**
	 * Método que establece el valor de la propiedad amountChunk
	 * 
	 * @param amountChunk,
	 *            valor que se estable para la propiedad amountChunk
	 */
	public void setAmountChunk(int amountChunk) {
		this.amountChunk = amountChunk;
	}

	/**
	 * Método que obtiene el valor de la propiedad chunk
	 * 
	 * @return valor de la propiedad chunk
	 */
	public String getChunk() {
		return chunk;
	}

	/**
	 * Método que establece el valor de la propiedad chunk
	 * 
	 * @param chunk,
	 *            valor que se estable para la propiedad chunk
	 */
	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

	/**
	 * Método que obtiene el valor de la propiedad seed
	 * 
	 * @return valor de la propiedad seed
	 */
	public String getSeed() {
		return seed;
	}

	/**
	 * Método que establece el valor de la propiedad seed
	 * 
	 * @param seed,
	 *            valor que se estable para la propiedad seed
	 */
	public void setSeed(String seed) {
		this.seed = seed;
	}
}
