package projektkurs.raster.extra;

import projektkurs.inventory.Inventory;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.world.Spielfeld;

/**
 * ExtraInformation einer Kiste.
 */
public class ExtraInformationKiste extends ExtraInformation {

	/**
	 * Das Inventar.
	 */
	private Inventory inventar;

	/**
	 * Konstruktor.
	 */
	public ExtraInformationKiste(Spielfeld map, int x, int y) {
		super(map, x, y);
		inventar = new Inventory(Integers.CHEST_SIZE);
	}

	@Override
	public String getInternalName() {
		return "chest";
	}

	/**
	 * Das Inventar der Kiste.
	 *
	 * @return Inventar.
	 */
	public Inventory getInventar() {
		return inventar;
	}

	/**
	 * Setzt das Inventar der Kiste.
	 *
	 * @param inventar Inventar
	 */
	public void setInventar(Inventory inventar) {
		this.inventar = inventar;
	}

	@Override
	public void load(SaveData data) {
		super.load(data);
		inventar = Inventory.load(data.getSaveData(Strings.EXTRA_INV));
	}

	@Override
	public void write(SaveData data) {
		super.write(data);
		data.set(Strings.EXTRA_INV, inventar.write());
	}

}
