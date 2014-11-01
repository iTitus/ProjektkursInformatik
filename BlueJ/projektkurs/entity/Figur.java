package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.lib.Integers;
import projektkurs.util.Inventory;

/**
 * Eine Spielerfigur
 * 
 * @author Niklas, Vladimir, Elena, Christopher
 * @version 11.09.2014
 */
public class Figur extends Entity {

	private Inventory inventar;

	/**
	 * Konstruktor für Figuren
	 * 
	 * @param posX
	 * @param posY
	 * @param image
	 */
	public Figur(int posX, int posY, BufferedImage image) {
		super(posX, posY, image);
		inventar = new Inventory(Integers.INVENTARGROESSE);
	}

	/**
	 * Das Inventar
	 * 
	 * @return
	 */
	public Inventory getInventory() {
		return inventar;
	}
}
