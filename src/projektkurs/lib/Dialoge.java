package projektkurs.lib;

import java.util.HashMap;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.part.DialogPart;
import projektkurs.util.Init;

/**
 * Alle Dialoge.
 */
public final class Dialoge {

    /**
     * Die Zurück-Mappings.
     */
    public static final HashMap<Dialog, String> BACK_MAPPINGS = new HashMap<Dialog, String>();
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Dialog> MAPPINGS = new HashMap<String, Dialog>();
    /**
     * Test-Dialog.
     */
    public static Dialog test;

    /**
     * Initialisiert alle Dialoge.
     */
    @Init
    public static void init() {

        test = new Dialog(new DialogPart("test1.good", 10, 0, true, 3), new DialogPart("test2.bad", -10, 0, false, 1));
        registerMapping("test", test);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param d
     *            Dialog
     */
    private static void registerMapping(String name, Dialog d) {
        MAPPINGS.put(name, d);
        BACK_MAPPINGS.put(d, name);
    }

    /**
     * Nicht instanziierbar.
     */
    private Dialoge() {
    }

}
