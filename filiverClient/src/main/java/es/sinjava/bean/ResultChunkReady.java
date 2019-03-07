/**
 * ﻿Copyright (C) 2019 Gobierno de Aragón
 * License: EUPL (version 1.1 or later). Ver LICENSE.txt Para los detalles.
 */
package es.sinjava.bean;

/**
 * @author agaudioso
 * 
 */
public class ResultChunkReady {

	private String seed;
	private String host;
	private String destiny;
	private String resultCreateDocument;
	private int nextChunk;

	/**
	 * M�todo que obtiene el valor de la propiedad nextChunk
	 * 
	 * @return valor de la propiedad nextChunk
	 */
	public int getNextChunk() {
		return nextChunk;
	}

	/**
	 * M�todo que establece el valor de la propiedad nextChunk
	 * 
	 * @param nextChunk,
	 *            valor que se estable para la propiedad nextChunk
	 */
	public void setNextChunk(int nextChunk) {
		this.nextChunk = nextChunk;
	}

	/**
	 * M�todo que obtiene el valor de la propiedad seed
	 * 
	 * @return valor de la propiedad seed
	 */
	public String getSeed() {
		return seed;
	}

	/**
	 * M�todo que establece el valor de la propiedad seed
	 * 
	 * @param seed,
	 *            valor que se estable para la propiedad seed
	 */
	public void setSeed(String seed) {
		this.seed = seed;
	}

	/**
	 * M�todo que obtiene el valor de la propiedad host
	 * 
	 * @return valor de la propiedad host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * M�todo que establece el valor de la propiedad host
	 * 
	 * @param host,
	 *            valor que se estable para la propiedad host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * M�todo que obtiene el valor de la propiedad destiny
	 * 
	 * @return valor de la propiedad destiny
	 */
	public String getDestiny() {
		return destiny;
	}

	/**
	 * M�todo que establece el valor de la propiedad destiny
	 * 
	 * @param destiny,
	 *            valor que se estable para la propiedad destiny
	 */
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	/**
	 * M�todo que obtiene el valor de la propiedad resultCreateDocument
	 * 
	 * @return valor de la propiedad resultCreateDocument
	 */
	public String getResultCreateDocument() {
		return resultCreateDocument;
	}

	/**
	 * M�todo que establece el valor de la propiedad resultCreateDocument
	 * 
	 * @param resultCreateDocument,
	 *            valor que se estable para la propiedad resultCreateDocument
	 */
	public void setResultCreateDocument(String resultCreateDocument) {
		this.resultCreateDocument = resultCreateDocument;
	}
}