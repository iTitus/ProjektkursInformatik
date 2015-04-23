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
    private static int value;

    /**
     * Der Wert der Dialoge.
     *
     * @return Wert
     */
    public static int getValue() {
        return value;
    }

    public static void setValue(int i) {
        DialogManager.value = i;
    }

    /**
     * Startet einen Dialog.
     *
     * @param d
     *            Dialog
     */
    public static void startDialog(Dialog d, Entity e) {
        startDialog(d, Main.getPlayer(), e);
    }

    /**
     * Startet einen Dialog.
     *
     * @param d
     *            Dialog
     */
    public static void startDialog(Dialog d, Entity e1, Entity e2) {
        Main.openGui(new GuiDialogChooser(d, e1, e2));
    }

    /**
     * Konstruktor.
     */
    private DialogManager() {
    }
}
