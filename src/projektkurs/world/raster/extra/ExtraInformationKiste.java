package projektkurs.world.raster.extra;

import projektkurs.inventory.Inventory;
import projektkurs.lib.Integers;

/**
 * Das Kisteninventar
 */
public class ExtraInformationKiste extends ExtraInformation {

	private final Inventory inventar;

	// private boolean used;

	/**
	 * Konstruktor
	 */
	public ExtraInformationKiste() {
		inventar = new Inventory(Integers.KISTENGROESSE);
	}

	/**
	 * Das Inventar der Kiste
	 *
	 * @return
	 */
	public Inventory getInventar() {
		return inventar;
	}

	// public boolean isUsed() {
	// return used;
	// }
	//
	// public void setUsed(boolean used) {
	// this.used = used;
	// }

}
