package projektkurs.dialog;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.gui.GuiDialogChooser;
import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;

/**
 * Managt alle Dialoge.
 */
public final class DialogManager implements ISaveable {

    /**
     * Der aktuelle Wert der Dialoge.
     */
    private int value;

    /**
     * Der Wert der Dialoge.
     *
     * @return Wert
     */
    public int getValue() {
        return value;
    }

    @Override
    public void load(SaveData data) {
        value = data.getInteger("value");
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Startet einen Dialog.
     *
     * @param d
     *            Dialog
     */
    public void startDialog(Dialog d, Entity e) {
        startDialog(d, Main.getPlayer(), e);
    }

    /**
     * Startet einen Dialog.
     *
     * @param d
     *            Dialog
     */
    public void startDialog(Dialog d, Entity e1, Entity e2) {
        Main.openGui(new GuiDialogChooser(d, e1, e2));
    }

    @Override
    public void write(SaveData data) {
        data.set("value", value);
    }
}
