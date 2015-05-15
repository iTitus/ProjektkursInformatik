package projektkurs.raster.extra;

import projektkurs.inventory.Inventory;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.world.Spielfeld;

/**
 * ExtraInformation einer Kiste.
 */
public class ExtraInformationChest extends ExtraInformation {

    /**
     * Das Inventar.
     */
    private Inventory inventory;

    public ExtraInformationChest(Spielfeld map) {
        super(map);
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public ExtraInformationChest(Spielfeld map, int x, int y) {
        super(map, x, y);
        inventory = new Inventory(Integers.CHEST_SIZE);
    }

    /**
     * Das Inventar der Kiste.
     *
     * @return Inventar.
     */
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        inventory = Inventory.load(data.getSaveData(Strings.EXTRA_INV));
    }

    /**
     * Setzt das Inventar der Kiste.
     *
     * @param inventory
     *            Inventar
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.EXTRA_INV, inventory.write());
    }

}
