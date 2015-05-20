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
    public static Dialog LVmFaehrmann;
    public static Dialog LVmFaehrmannOne;
    public static Dialog LVmFaehrmannTwo;
    public static Dialog LVmFischer;
    public static Dialog LVmFischerStd;
    public static Dialog LVmFrau;
    public static Dialog LVmFrauEnde;
    public static Dialog LVmFrauOne;
    public static Dialog LVmFrauThree;
    public static Dialog LVmFrauTwo;
    public static Dialog LVmHexerzirkel;
    public static Dialog LVmHexerzirkelOne;
    public static Dialog LVmHexerzirkelTwo;
    public static Dialog LVmJungeAmWegesrand;
    public static Dialog LVmJungeAmWegesrandOne;
    public static Dialog LVmJungeAmWegesrandTwo;
    public static Dialog LVmNest;
    public static Dialog LVmNestOne;
    public static Dialog LVmSchranke;
    public static Dialog LVmTrashCan;
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

        LVmFaehrmann = new Dialog("LVmFaehrmann", new DialogPart("LVmFaehrmannNull", 1 << 1, 0 | 1 << 2 | 1 << 3, 4));
        registerMapping(LVmFaehrmann);

        LVmFaehrmannOne = new Dialog("LVmFaehrmannOne", new DialogPart("LVmFaehrmannOneNull", 1 << 1 | 1 << 4 | 1 << 5 | 1 << 6 | 1 << 7 | 1 << 8, 2));
        registerMapping(LVmFaehrmannOne);

        LVmFaehrmannTwo = new Dialog("LVmFaehramnnTwo", new DialogPart("LVmFaehrmannTwoNull", 1 << 9, 2).setEndAction(ReflectionUtil.getMethod(Scripts.class, "removeItem", ItemStack.class), new ItemStack(Items.gramophoneItem)));
        registerMapping(LVmFaehrmannTwo);

        LVmJungeAmWegesrand = new Dialog("LVmJungeAmWegesrand", new DialogPart("LVmJungeAmWegesrandNull", 1 << 2, 0 | 1 << 1 | 1 << 3, 2));
        registerMapping(LVmJungeAmWegesrand);

        LVmJungeAmWegesrandOne = new Dialog("LVmJungeAmWegesrandOne", new DialogPart("LVmJungeAmWegesrandOneNull", 1 << 7, 1 << 5 | 1 << 6 | 1 << 8, 4).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptJunge")));
        registerMapping(LVmJungeAmWegesrandOne);

        LVmJungeAmWegesrandTwo = new Dialog("LVmJungeAmWegesrandTwo", new DialogPart("LVmJungeAmWegesrandTwoNull", 1 << 7, 1));
        registerMapping(LVmJungeAmWegesrandTwo);

        LVmFrau = new Dialog("LVmFrau", new DialogPart("LVmFrauNull", 0 | 1 << 1 | 1 << 2, 1)); // );
        registerMapping(LVmFrau);

        LVmFrauOne = new Dialog("LVmFrauOne", new DialogPart("LVmFrauOneNull", 1 << 3, 1 << 1 | 1 << 4, 3), new DialogPart("LVmFrauOneEins", 1 << 3, 1 << 1 | 1 << 4, 2), new DialogPart("LVmFrauOneZwei", 1 << 3, 1 << 1 | 1 << 4, 2),
                new DialogPart("LVmFrauOneDrei", 1 << 3, 1 << 1 | 1 << 4, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutscenetwo")));
        registerMapping(LVmFrauOne);

        LVmFrauTwo = new Dialog("LVmFrauTwo", new DialogPart("LVmFrauTwoNull", 1 << 3 | 1 << 4, 3));
        registerMapping(LVmFrauTwo);

        LVmFrauThree = new Dialog("LVmFrauThree", new DialogPart("LVmFrauThreeNull", 1 << 7, 1 << 5 | 1 << 7 | 1 << 8, 9).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWoman")));
        registerMapping(LVmFrauThree);

        // Neu Frau Dialog
        LVmFrauEnde = new Dialog("LVmFrauEnde", new DialogPart("LVmFrauEndeNull", 1 << 6 | 1 << 9, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutsceneThree")));
        registerMapping(LVmFrauEnde);

        LVmFischer = new Dialog("LVmFischer", new DialogPart("LVmFischerNull", 1 << 3, 6), new DialogPart("LVmFischerEins", 1 << 3, 5), new DialogPart("LVmFischerZwei", 1 << 3, 5), new DialogPart("LVmFischerDrei", 1 << 3, 2), new DialogPart("LVmFischerVier", 1 << 3, 2), new DialogPart("LVmFischerFuenf", 1 << 4,
                1 << 3, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptFisher")));
        registerMapping(LVmFischer);

        // Neu Fischer Dialog

        LVmFischerStd = new Dialog("LVmFischerStd", new DialogPart("LVmFischerStdNull", 1 << 4, 1));
        registerMapping(LVmFischerStd);

        LVmHexerzirkel = new Dialog("LVmHexerzirkel", new DialogPart("LVmHexerzirkelNull", 1 << 5, 1 << 4 | 1 << 5, 2), new DialogPart("LVmHexerzirkelEins", 1 << 5, 1 << 4 | 1 << 5, 4), new DialogPart("LVmHexerzirkelZwei", 1 << 5, 1 << 4 | 1 << 5, 7));
        registerMapping(LVmHexerzirkel);

        LVmHexerzirkelOne = new Dialog("LVmHexerzirkelOne", new DialogPart("LVmHexerzirkelOneNull", 1 << 5 | 1 << 6 | 1 << 7, 2));
        registerMapping(LVmHexerzirkelOne);

        LVmHexerzirkelTwo = new Dialog("LVmHexerzirkelTwo", new DialogPart("LVmHexerzirkelTwoNull", 1 << 9, 1 << 8, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWitchCoul")));
        registerMapping(LVmHexerzirkelTwo);

        LVmTrashCan = new Dialog("LVmTrashCan", new DialogPart("LVmTrashCanNull", 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptThrashCan")));
        registerMapping(LVmTrashCan);

        // Gleichzusetzten mit neccValue von Hexerzirkel, da DialogValue Sich im Laufe ver�ndert // Testweise, da VilleCar sich auf Stumm gestellt hatte
        LVmNest = new Dialog("LVmNest", new DialogPart("LVmNestNull", 1 << 4 | 1 << 5, 1));
        registerMapping(LVmNest);

        LVmNestOne = new Dialog("LVmNestOne", new DialogPart("LVmNestOneNull", 1 << 8, 1 << 7, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptNest")));
        registerMapping(LVmNestOne);

        LVmSchranke = new Dialog("LVmSchranke", new DialogPart("LVmSchrankeNull", 1));
        registerMapping(LVmSchranke);
        // LVmStein = new Dialog("LVmStein", new DialogPart("LVmSteinNull",1)); registerMapping(LVmStein);
        // LVmTeddy = new Dialog("LVmTeddy", new DialogPart("LVmTeddyNull",1)); registerMapping(LVmTeddy);
        // LVmYoyo = new Dialog("LVmYoyo", new DialogPart("LVmYoyoNull",1)); registerMapping(LVmYoyo);
        // LVmKaugummi = new Dialog("LVmKaugummi", new DialogPart("LVmKaugummiNull",1)); registerMapping(LVmKaugummi);
        // LVmNetz = new Dialog("LVmNetz", new DialogPart("LVmNetzNull",1)); registerMapping(LVmNetz);
        // LVmMesser = new Dialog("LVmMesser", new DialogPart("LVmMesserNull",1)); registerMapping(LVmMesser);

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
