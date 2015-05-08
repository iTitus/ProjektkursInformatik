package projektkurs.dialog;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.gui.GuiDialogChooser;

/**
 * Managt alle Dialoge.
 */
public final class DialogManager {

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
}
