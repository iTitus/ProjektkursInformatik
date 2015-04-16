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

    public static Dialog LVmFaehrmann;
    public static Dialog LVmFaehrmannOne;
    public static Dialog LVmFaehrmannTwo;
    public static Dialog LVmFischer;
    public static Dialog LVmFrau;
    public static Dialog LVmFrauOne;
    public static Dialog LVmFrauThree;
    public static Dialog LVmFrauTwo;
    public static Dialog LVmHexerzirkel;
    public static Dialog LVmHexerzirkelOne;
    public static Dialog LVmHexerzirkelTwo;
    public static Dialog LVmJungeAmWegesrand;
    public static Dialog LVmJungeAmWegesrandOne;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Dialog> MAPPINGS = new HashMap<String, Dialog>();
    /**
     * Test-Dialog.
     */
    public static Dialog test;

    /**
     * Das Pair, das alle Dialoge enthaelt.
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

        LVmFaehrmann = new Dialog("LVmFaehrmann", new DialogPart("LVmFaehrmannEins", 1, 0, 2));
        registerMapping(LVmFaehrmann);

        LVmFaehrmannOne = new Dialog("LVmFaehrmannOne", new DialogPart("LVmFaehrmannOne", 0, 1, 2));
        registerMapping(LVmFaehrmannOne);

        LVmFaehrmannTwo = new Dialog("LVmFaehramnnTwo", new DialogPart("LVmFaehrmannTwo", 1 << 19, 1 << 18, 2));
        registerMapping(LVmFaehrmannTwo);

        LVmJungeAmWegesrand = new Dialog("JungeAmWegesrand", new DialogPart("JungeAmWegesrandEins", 0b10, 0, 2));
        registerMapping(LVmJungeAmWegesrand);

        LVmJungeAmWegesrandOne = new Dialog("JungeAmWegesrandOne", new DialogPart("JungeAmWegesrandOneEins", 1 << 16, 1 << 13 | 1 << 12 | 1 << 14, 6));
        registerMapping(LVmJungeAmWegesrandOne);

        LVmFrau = new Dialog("LVmFrau", new DialogPart("LVmFrauEins", 0b100, 1, 2), new DialogPart("LVmFrauZwei", 0b1000, 1, 2), new DialogPart("LVmFrauDrei", 1 << 4, 1, 2), new DialogPart("LVmFrauVier", 1 << 5, 1, 1));
        registerMapping(LVmFrau);

        LVmFrauOne = new Dialog("LVmFrauOne", new DialogPart("LVmFrauOneEins", 0, 0b100 | 0b1000 | 1 << 4 | 1 << 5, 3));
        registerMapping(LVmFrauOne);

        LVmFrauTwo = new Dialog("LVmFrauTwo", new DialogPart("LVmFrauTwoEins", 1 << 15, 1 << 12 | 1 << 13 | 1 << 14, 4));
        registerMapping(LVmFrauTwo);

        LVmFrauThree = new Dialog("LVmFrauThree", new DialogPart("LVmFrauThree", 0, 1 << 19, 1));
        registerMapping(LVmFrauThree);

        LVmFischer = new Dialog("LVmFischer", new DialogPart("LVmFischer", 1 << 6, 1 | 2 | 4, 5), new DialogPart("LVmFischerZwei", 1 << 7, 1 | 2 | 4, 5), new DialogPart("LVmFischerDrei", 1 << 8, 1 | 2 | 4, 2), new DialogPart("LVmFischerVier", 1 << 9, 1 | 2 | 4, 2), new DialogPart("LVmFischerFuenf", 1 << 10, 1 | 2 | 4,
                2), new DialogPart("LVmFischerSechs", 1 << 11, 1 | 2 | 4, 1));
        registerMapping(LVmFischer);

        LVmHexerzirkel = new Dialog("LVmHexerzirkel", new DialogPart("LVmHexerzirkelEins", 1 << 12, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 2), new DialogPart("LVmHexerzirkelZwei", 1 << 13, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 4), new DialogPart("LVmHexerzirkelDrei", 1 << 14, 1 << 6
                | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 5));
        registerMapping(LVmHexerzirkel);

        LVmHexerzirkelOne = new Dialog("LVmHexerzirkelOne", new DialogPart("LVmHexerzirkelOne", 1 << 17, 1 << 12 | 1 << 13 | 1 << 14, 1));
        registerMapping(LVmHexerzirkelOne);

        LVmHexerzirkelTwo = new Dialog("LVmHexerzirkelTwo", new DialogPart("LVmHexerzirkelTwo", 1 << 18, 1 << 15 | 1 << 16 | 1 << 17, 1));
        registerMapping(LVmHexerzirkelTwo);

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
            throw new IllegalArgumentException("Unable to register Dialog " + d);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Dialoge() {
    }

}
