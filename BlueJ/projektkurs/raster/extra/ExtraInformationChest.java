package projektkurs.raster.extra;

import projektkurs.inventory.Inventory;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

/**
 * ExtraInformation einer Kiste.
 */
public class ExtraInformationChest extends ExtraInformation {

    /**
     * Das Inventar.
     */
    private Inventory inventory;

    /**
     * Konstruktor.
     */
    public ExtraInformationChest() {
        inventory = new Inventory(Integers.CHEST_SIZE);
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
     * @param inventar
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