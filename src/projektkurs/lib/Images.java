package projektkurs.lib;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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
			redNPC, item_42, nuke, key, slot, slot_highlight, door_NS, door_WE,
			door_open_NS, door_open_WE, finish, destroyedRaster, fire[], healthpotion;

	public static void flushAll() {
		for (BufferedImage img : MAPPINGS.values()) {
			if (img != null)
				img.flush();
		}
	}

	/**
	 * Laedt alle Bilder
	 */
	@Init(state = State.RESOURCES)
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
		item_42 = loadImage("item_42.png");
		MAPPINGS.put("item_42", item_42);
		nuke = loadImage("nuke.png");
		MAPPINGS.put("nuke", nuke);
		key = loadImage("key.png");
		MAPPINGS.put("key", key);
		slot = loadImage("slot.png");
		MAPPINGS.put("slot", slot);
		slot_highlight = loadImage("slot_highlight.png");
		MAPPINGS.put("slot_highlight", slot_highlight);
		door_NS = loadImage("door.png");
		MAPPINGS.put("door_NS", door_NS);
		door_WE = loadImageRotate90("door.png");
		MAPPINGS.put("door_WE", door_WE);
		door_open_NS = loadImage("door_open.png");
		MAPPINGS.put("door_open_NS", door_open_NS);
		door_open_WE = loadImageRotate90("door_open.png");
		MAPPINGS.put("door_open_WE", door_open_WE);
		finish = loadImage("finish.png");
		MAPPINGS.put("finish", finish);
		destroyedRaster = loadImage("destroyed.png");
		MAPPINGS.put("destroyedRaster", destroyedRaster);
		fire = loadImageArray("fire_%d.png", 4);
		for (int i = 0; i < fire.length; i++) {
			MAPPINGS.put("fire_" + i, fire[i]);
		}
		healthpotion = loadImage("healthpotion.png");
		MAPPINGS.put("healthpotion", healthpotion);
	}

	public static void setCharakterImage(BufferedImage img) {
		charakter = img;

	}

	/**
	 * Lädt ein Bild
	 *
	 * @param name
	 *            Name des Bildes
	 * @return Image das geladene Bild
	 */
	private static BufferedImage loadImage(String name) {

		String path = "resources/images/" + name;

		BufferedImage img = null;
		try {
			img = ImageIO.read(Main.class.getResource(path));
			Logger.info("Successfully loaded image '" + name + "'");
		} catch (Throwable t1) {
			try (InputStream stream = Main.class.getResourceAsStream(path)) {
				img = ImageIO.read(stream);
				Logger.info("Successfully loaded image '" + name + "'");
			} catch (Throwable t2) {
				Logger.logThrowable("Unable to load image '" + name + "'", t2);
			}
		}
		return img;
	}

	private static BufferedImage[] loadImageArray(String name, int length) {
		BufferedImage[] images = new BufferedImage[length];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage(String.format(name, i));
		}
		return images;
	}

	private static BufferedImage loadImageRotate90(String name) {
		BufferedImage img = loadImage(name);
		if (img != null) {
			BufferedImage rotated = new BufferedImage(img.getWidth(),
					img.getHeight(), img.getType());
			Graphics2D g = (Graphics2D) rotated.getGraphics();
			g.setTransform(AffineTransform.getQuadrantRotateInstance(1));
			g.drawImage(img, 0, -img.getWidth(), null);
			return rotated;
		}
		return null;
	}
}
