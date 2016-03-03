package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogPart;
import projektkurs.item.ItemStack;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;

/**
 * Alle Dialoge.
 */
public final class Dialoge {

    public static Dialog CS_LVmFrau;
    public static Dialog LVmFaehrmannEnd;
    public static Dialog LVmFaehrmannNull;
    public static Dialog LVmFaehrmannOne;
    public static Dialog LVmFischerNull;
    public static Dialog LVmFischerStd;
    public static Dialog LVmFrauEnd;
    public static Dialog LVmFrauNull;
    public static Dialog LVmFrauOne;
    public static Dialog LVmFrauStd;
    public static Dialog LVmFrauThree;
    public static Dialog LVmFrauTwo;
    public static Dialog LVmHexerzirkelNull;
    public static Dialog LVmHexerzirkelOne;
    public static Dialog LVmHexerzirkelStd;
    public static Dialog LVmHexerzirkelTwo;
    public static Dialog LVmJungeAmWegesrandNull;
    public static Dialog LVmJungeAmWegesrandOne;
    public static Dialog LVmJungeAmWegesrandStd;
    public static Dialog LVmNestNull;
    public static Dialog LVmNestOne;
    public static Dialog LVmNestStd;
    public static Dialog LVmSchranke;
    public static Dialog LVmTrashCan;
    public static Dialog DiaFaehrmann;
    public static Dialog DiaBarkeeper;


    /*
     * public static Dialog LVmKaugummi; public static Dialog LVmMesser; public static Dialog LVmNetz; public static Dialog LVmStein; public static Dialog LVmTeddy; public static Dialog LVmYoyo;
     */

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
    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.dialogs", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Dialoge.
     */
    @Init
    public static void init() {

        CS_LVmFrau = new Dialog("CS_LVmFrau", new DialogPart("CS_LVmFrauNull", 14));
        registerMapping(CS_LVmFrau);

        LVmFaehrmannNull = new Dialog("LVmFaehrmannNull", new DialogPart("LVmFaehrmannNullnull", 1 | 1 << 1, 0, 4));
        registerMapping(LVmFaehrmannNull);

        LVmFrauNull = new Dialog("LVmFrauNull", new DialogPart("LVmFrauNullnull", 0, 2)); // changed sth.
        registerMapping(LVmFrauNull);

        LVmJungeAmWegesrandNull = new Dialog("LVmJungeAmWegesrandNull", new DialogPart("LVmJungeAmWegesrandNullnull", 0, 2));
        registerMapping(LVmJungeAmWegesrandNull);

        LVmFrauOne = new Dialog("LVmFrauOne", new DialogPart("LVmFrauOneNull", 1 << 2, 1 << 0 | 1 << 1, 3), new DialogPart("LVmFrauOneEins", 1 << 3, 1 << 0 | 1 << 1, 2), new DialogPart("LVmFrauOneZwei", 1 << 4, 1 << 0 | 1 << 1, 2),
                new DialogPart("LVmFrauOneDrei", 1 << 5, 1 << 0 | 1 << 1, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutscenetwo")));
        registerMapping(LVmFrauOne);

        LVmFaehrmannOne = new Dialog("LVmFaehrmannOne", new DialogPart("LVmFaehrmannOneNull", 1 << 0 | 1 << 1, 2));
        registerMapping(LVmFaehrmannOne);

        LVmFischerNull = new Dialog("LVmFischerNull", new DialogPart("LVmFischerNullnull", 1 << 6, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 6), new DialogPart("LVmFischerNullEins", 1 << 7, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 5), new DialogPart("LVmFischerNullZwei", 1 << 8, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 5),
                new DialogPart("LVmFischerNullDrei", 1 << 9, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 2), new DialogPart("LVmFischerNullVier", 1 << 10, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 2), new DialogPart("LVmFischerNullFuenf", 1 << 11, 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 1).setEndAction(ReflectionUtil.getMethod(
                        Scripts.class, "scriptFisher")));
        registerMapping(LVmFischerNull);

        LVmHexerzirkelNull = new Dialog("LVmHexerzirkelNull", new DialogPart("LVmHexerzirkelNullnull", 1 << 12, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 2), new DialogPart("LVmHexerzirkelNullEins", 1 << 13, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 4), new DialogPart(
                "LVmHexerzirkelNullZwei", 1 << 14, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 7));
        registerMapping(LVmHexerzirkelNull);

        LVmNestNull = new Dialog("LVmNestNull", new DialogPart("LVmNestNullnull", 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 1));
        registerMapping(LVmNestNull);

        LVmFrauTwo = new Dialog("LVmFrauTwo", new DialogPart("LVmFrauTwoNull", 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5, 3));
        registerMapping(LVmFrauTwo);

        LVmFischerStd = new Dialog("LVmFischerStd", new DialogPart("LVmFischerStdNull", 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 1));
        registerMapping(LVmFischerStd);

        LVmHexerzirkelOne = new Dialog("LVmHexerzirkelOne", new DialogPart("LVmHexerzirkelOneNull", 1 << 12 | 1 << 13 | 1 << 14, 2));
        registerMapping(LVmHexerzirkelOne);

        LVmFrauThree = new Dialog("LVmFrauThree", new DialogPart("LVmFrauThreeNull", 1 << 15, 1 << 12 | 1 << 13 | 1 << 14, 9).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWoman")));
        registerMapping(LVmFrauThree);

        LVmJungeAmWegesrandOne = new Dialog("LVmJungeAmWegesrandOne", new DialogPart("LVmJungeAmWegesrandOneNull", 1 << 16, 1 << 12 | 1 << 13 | 1 << 14, 5).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptJunge")));
        registerMapping(LVmJungeAmWegesrandOne);

        LVmNestOne = new Dialog("LVmNestOne", new DialogPart("LVmNestOneNull", 1 << 17, 1 << 15 | 1 << 16, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptNest")));
        registerMapping(LVmNestOne);

        LVmFrauStd = new Dialog("LVmFrauStd", new DialogPart("LVmFrauStdNull", 1 << 15, 1));
        registerMapping(LVmFrauStd);

        LVmJungeAmWegesrandStd = new Dialog("LVmJungeAmWegesrandStd", new DialogPart("LVmJungeAmWegesrandStdNull", 1 << 16, 1));
        registerMapping(LVmJungeAmWegesrandStd);

        LVmHexerzirkelTwo = new Dialog("LVmHexerzirkelTwo", new DialogPart("LVmHexerzirkelTwoNull", 1 << 18, 1 << 17, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWitchCoul")));
        registerMapping(LVmHexerzirkelTwo);

        LVmNestStd = new Dialog("LVmNestStd", new DialogPart("LVmNestStdNull", 1 << 17, 1));
        registerMapping(LVmNestStd);

        LVmHexerzirkelStd = new Dialog("LVmHexerzirkelStd", new DialogPart("LVmHexerzirkelStdNull", 1 << 18, 1));
        registerMapping(LVmHexerzirkelStd);

        LVmFaehrmannEnd = new Dialog("LVmFaehrmannEnd", new DialogPart("LVmFaehrmannEndNull", 1 << 19, 1 << 18, 2).setEndAction(ReflectionUtil.getMethod(Scripts.class, "removeItem", ItemStack.class), new ItemStack(Items.gramophoneItem)));
        registerMapping(LVmFaehrmannEnd);

        LVmFrauEnd = new Dialog("LVmFrauEnd", new DialogPart("LVmFrauEndNull", 1 << 19, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutsceneThree")));
        registerMapping(LVmFrauEnd);

        LVmTrashCan = new Dialog("LVmTrashCan", new DialogPart("LVmTrashCanNull", 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptThrashCan")));
        registerMapping(LVmTrashCan);

        LVmSchranke = new Dialog("LVmSchranke", new DialogPart("LVmSchrankeNull", 1));
        registerMapping(LVmSchranke);

        DiaFaehrmann = new Dialog("DiaFaehrmann", new DialogPart("FaehrmannNull", 2));
        registerMapping(DiaFaehrmann);
        
        DiaBarkeeper = new Dialog("DiaBarkeeper", new DialogPart("DiaBarkeeperNull", 2));
        registerMapping(DiaBarkeeper);
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
