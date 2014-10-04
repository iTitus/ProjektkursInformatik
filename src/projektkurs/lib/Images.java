package projektkurs.lib;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import projektkurs.Main;

/**
 * Hier alles was mit Bildern zu tun hat
 */
public class Images {

	public static BufferedImage rasen, wand, defaultCharakter, baum, kiste;

	public static BufferedImage charakter;

	/**
	 * Laedt alle Bilder
	 */
	public static void init() {
		charakter = defaultCharakter = loadImage("charakter.png");
		rasen = loadImage("rasen.png");
		wand = loadImage("wand.png");
		baum = loadImage("baum.png");
		kiste = loadImage("kiste.png");

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

	public static void setCharakterImage(BufferedImage img) {
		System.out.println(img.toString());
		charakter = img;

	}
}
