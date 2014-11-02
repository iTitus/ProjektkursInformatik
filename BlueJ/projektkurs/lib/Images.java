package projektkurs.lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import projektkurs.Main;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;

/**
 * Hier alles was mit Bildern zu tun hat
 */
public class Images {

	/**
	 * Userauswahl
	 */
	public static BufferedImage charakter;

	public static final HashMap<String, BufferedImage> MAPPINGS = new HashMap<String, BufferedImage>();

	/**
	 * Werden aus den Resourcen geladen
	 */
	public static BufferedImage rasen, wand, defaultCharakter, baum, kiste,
			redNPC;

	public static void flushAll() {
		for (BufferedImage img : MAPPINGS.values())
			img.flush();
	}

	/**
	 * Laedt alle Bilder
	 */
	@Init(state = State.PRE)
	public static void init() {
		charakter = defaultCharakter = loadImage("charakter.png");
		MAPPINGS.put("defaultCharakter", defaultCharakter);
		redNPC = loadImage("redNPC.png");
		MAPPINGS.put("redNPC", redNPC);
		rasen = loadImage("rasen.png");
		MAPPINGS.put("rasen", rasen);
		wand = loadImage("wand.png");
		MAPPINGS.put("wand", wand);
		baum = loadImage("baum.png");
		MAPPINGS.put("baum", baum);
		kiste = loadImage("kiste.png");
		MAPPINGS.put("kiste", kiste);
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
			Logger.info("Successfully loaded image: " + name);
		} catch (Exception e) {
			Logger.logThrowable("Unable to load image '" + name + "': ", e);
		}
		return img;
	}
}
