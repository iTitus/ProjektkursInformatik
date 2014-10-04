package projektkurs.entity;

import projektkurs.lib.Integers;
import projektkurs.lib.Inventory;

/**
 * Figurerstellung
 * 
 * @author Niklas, Vladimir, Elena, Christopher
 * @version 11.09.2014
 */
public class Figur {

	private Inventory inventar;

	/**
	 * Konstruktor für die Figur
	 */
	public Figur() {

		inventar = new Inventory(Integers.INVENTARGROESSE);
	}

	/**
	 * Wird überprüft, ob man mit einem Gegenstand interagieren kann
	 * 
	 * @param intermoeglichkeit
	 *            aus der Klasse Welt
	 */
	// private void interaktion(boolean intermoeglichkeit) {
	// if (intermoeglichkeit == true) {
	// }
	//
	// }

	/**
	 * Das Inventar
	 * 
	 * @return
	 */
	public Inventory getInventory() {
		return inventar;
	}
}
