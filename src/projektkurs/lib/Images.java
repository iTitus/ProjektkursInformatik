package projektkurs.lib;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import projektkurs.Main;
import projektkurs.lib.Init.State;

/**
 * Hier alles was mit Bildern zu tun hat
 */
public class Images {

	public static BufferedImage charakter, test_guy;

	public static BufferedImage rasen, wand, defaultCharakter, baum, kiste;

	/**
	 * Laedt alle Bilder
	 */
	@Init(state = State.PRE)
	public static void init() {
		charakter = defaultCharakter = loadImage("charakter.png");
		test_guy = loadImage("test.png");

		rasen = loadImage("rasen.png");
		wand = loadImage("wand.png");
		baum = loadImage("baum.png");
		kiste = loadImage("kiste.png");

	}

	public static void setCharakterImage(BufferedImage img) {
		charakter = img;

	}

	/**
	 * Laedt ein Bild
	 * 
	 * @param name
	 *            Name des Bildes
	 * @return Image das geladene Bild
	 */
	private static BufferedImage loadImage(String name) {

		BufferedImage img = null;

		try {
			img = ImageIO.read(Main.class.getResource("resources"
					+ File.separator + "images" + File.separator + name));
		} catch (Exception e) {
			System.err.println("[ERROR] Unable to load image with name: "
					+ name);
		}
		return img;
	}
}
