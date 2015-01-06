package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogPart;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Dialoge.
 */
public final class Dialoge {

    /**
     * Die Mappings.
     */
    public static final HashMap<String, Dialog> MAPPINGS = new HashMap<String, Dialog>();
    /**
     * Test-Dialog.
     */
    public static Dialog test;

    /**
     * Das Pair, das alle Dialoge enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.dialogs", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Dialoge.
     */
    @Init
    public static void init() {

        test = new Dialog("testDialog", new DialogPart("test1.good", 10, 0, true, 3), new DialogPart("test2.bad", -10, 0, false, 1));
        registerMapping(test);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param d
     *            Dialog
     */
    private static void registerMapping(Dialog d) {
        if (d != null && !MAPPINGS.containsKey(d.getName())) {
            MAPPINGS.put(d.getName(), d);
        } else {
            Logger.warn("Unable to register Dialog", d);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Dialoge() {
    }

}
