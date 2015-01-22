package projektkurs.dialog;

/**
 * Managt alle Dialoge.
 */
public final class DialogManager {

    /**
     * Der aktuelle Wert der Dialoge.
     */
    private static int value;

    /**
     * Aendert den Wert der Dialoge.
     *
     * @param by
     *            Aenderung
     */
    public static void changeValue(int by) {
        value += by;
    }

    /**
     * Der Wert der Dialoge.
     *
     * @return Wert
     */
    public static int getValue() {
        return value;
    }

    /**
     * Konstruktor.
     */
    private DialogManager() {
    }
}
