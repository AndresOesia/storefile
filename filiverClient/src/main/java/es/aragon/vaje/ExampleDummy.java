package es.aragon.vaje;

import java.io.File;
import java.io.IOException;

import es.aragon.vaje.client.VajeClient;

public class ExampleDummy {

	public static void main(String... args) {

		File demo = new File(App.class.getClassLoader().getResource("sharleen.jpg").getFile());
		String nif = "11111111H";
		String app = "VAJE";
		String filename = "teresa.jpg";
		String mimeType = "image/jpg";
		String url = "http://localhost:8080/dummy/rest/fileManager/VAJE/1/11111111H";

		VajeClient vajeClient = new VajeClient(demo, nif, app, filename, mimeType, url);

		try {
			vajeClient.executeStore();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
