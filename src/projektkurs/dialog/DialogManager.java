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
    private static long value;

    /**
     * Aendert den Wert der Dialoge.
     *
     * @param by
     *            Aenderung
     */
    public static void changeValue(long by) {
        value |= by;
    }

    /**
     * Der Wert der Dialoge.
     *
     * @return Wert
     */
    public static long getValue() {
        return value;
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
