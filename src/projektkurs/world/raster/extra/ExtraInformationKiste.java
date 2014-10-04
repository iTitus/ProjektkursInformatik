package projektkurs.world.raster.extra;

import projektkurs.lib.Integers;
import projektkurs.lib.Inventory;

/**
 * Das Kisteninventar
 * 
 */
public class ExtraInformationKiste extends ExtraInformation {

	private Inventory inventar;

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
