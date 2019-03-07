package es.aragon.vaje;

import java.io.File;
import java.io.IOException;

import es.aragon.vaje.client.VajeClient;

public class ExampleClient {

	public static void main(String... args) {

		File demo = new File(App.class.getClassLoader().getResource("titulospdf.pdf").getFile());
		String nif = "00000000T";
		String app = "VAJE";
		String filename = "angel.jpg";
		String mimeType = "application/pdf";
		String url = "http://localhost:7001/vaje_core/rest/fileManager/VAJE/1/11111111H";

		VajeClient vajeClient = new VajeClient(demo, nif, app, filename, mimeType, url);

		try {
			vajeClient.executeStore();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
