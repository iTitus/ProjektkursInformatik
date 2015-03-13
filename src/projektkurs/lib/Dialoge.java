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
    public static Dialog LVmFischer;
    public static Dialog LVmHexerzirkel;
    public static Dialog LVmFrau;
    public static Dialog LVmFrauOne;
    public static Dialog LVmFrauTwo;
    public static Dialog LVmFrauThree;
    public static Dialog LVmFaehrmann;
    public static Dialog LVmFaehrmannOne;
    public static Dialog LVmFaehrmannTwo;
    public static Dialog LVmHexerzirkelOne;
    public static Dialog LVmJungeAmWegesrand;
    public static Dialog LVmJungeAmWegesrandOne;
    public static Dialog LVmJungeAmWegesrandTwo;

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

        /*
         * test = new Dialog("testDialog", new DialogPart("test1.good", 10, 0, true, 3), new DialogPart("test2.bad", -10, 0, false, 1));
         */
        registerMapping(test);

        // Neuer Dialog (Fischer) //[3]

        // LVmFischer = new Dialog("LVmFischer",#, new DialoPart("LVmFischer",5), new DialogPart("LVmFischerZwei",5 ), new DialogPart("LVmFischerDrei",2 ), new DialogPart("LVmFischerVier",2 ), new DialogPart("LVmFischerFuenf", 2 ), new DialogPart("LVmFischerSechs", 1 ));
        LVmFischer = new Dialog("LVmFischer", new DialogPart("LVmFischer", 5), new DialogPart("LVmFischerZwei", 5), new DialogPart("LVmFischerDrei", 2), new DialogPart("LVmFischerVier", 2), new DialogPart("LVmFischerFuenf", 2), new DialogPart("LVmFischerSechs", 1));
        registerMapping(LVmFischer);

        // Neuer Dialog (Hexerzirkel) #TODO: Wenn Teddy(vollstuendig im Inventar, dann LVmHexerzirkelOne) // [4]

        LVmHexerzirkel = new Dialog("LVmHexerzirkel", new DialogPart("LVmHexerzirkelEins", 2), new DialogPart("LVmHexerzirkelZwei", 4), new DialogPart("LVmHexerzirkelDrei", 5));
        registerMapping(LVmHexerzirkel);

        // #TODO: // InventoryhasItemStack

        LVmHexerzirkelOne = new Dialog("LVmHexerzirkelOne", new DialogPart("LVmHexerzirkelOne", 1));
        registerMapping(LVmHexerzirkelOne);

        // Neuer Dialog (Frau) // [2]

        // LVmFrau = new Dialog("LVmFrau", 1 ,new DialogPart("LVmFrauEins",1,2,1), new DialogPart("LVmFrauZwei", 2), new DialogPart("LVmFrauDrei", 2), new DialogPart("LVmFrauVier", 1));
        LVmFrau = new Dialog("LVmFrau", new DialogPart("LVmFrauEins", 2), new DialogPart("LVmFrauZwei", 2), new DialogPart("LVmFrauDrei", 2), new DialogPart("LVmFrauVier", 1));
        registerMapping(LVmFrau);

        // TODO: Besonders Neuer Trigger von Noeten. Einer der prueft, ob Dialog bereits durchgefuehrt --> Neuen Dialog ueberschreiben

        LVmFrauOne = new Dialog("LVmFrauOne", new DialogPart("LVmFrauOneEins", 3));
        registerMapping(LVmFrauOne);

        // TODO: Aehnlich wie im Comment ueber diesem hier

        LVmFrauTwo = new Dialog("LVmFrauTwo", new DialogPart("LVmFrauTwoEins", 4));
        registerMapping(LVmFrauTwo);

        // InventoryHasItemStack_Trigger benoetigt (Einsammeln von Ohrringen) um naechsten Dialog zu ermoeglichen #FIXME

        LVmFrauThree = new Dialog("LVmFrauThree", new DialogPart("LVmFrauThree", 1));
        registerMapping(LVmFrauThree);

        // Neuer Dialog (Faehrmann) // [1]

        // LVmFaehrmann = new Dialog("LVmFaehrmann", 0 ,new DialogPart("LVmFaehrmannEins",1, 2, 0 ));
        LVmFaehrmann = new Dialog("LVmFaehrmann", new DialogPart("LVmFaehrmannEins", 2));
        registerMapping(LVmFaehrmann);

        // Auch hier wieder Super-Dupa "Dialog schon abgespielt, spiel einen neuen rueber-" Trigger nuetig !! Bis dato hier auch schon mal der Dialog an sich. #FIXME

        LVmFaehrmannOne = new Dialog("LVmFaehrmannOne", new DialogPart("LVmFaehrmannOne", 2));
        registerMapping(LVmFaehrmannOne);

        // TODO: Sobald Grammophon im Inventar (InventoryHasItemStack-Trigger)

        LVmFaehrmannTwo = new Dialog("LVmFaehramnnTwo", new DialogPart("LVmFaehrmannTwo", 2));
        registerMapping(LVmFaehrmannTwo);

        // Neuer Dialog (JungeAmWegesrand) // [5]

        LVmJungeAmWegesrand = new Dialog("JungeAmWegesrand", new DialogPart("JungeAmWegesrandEins", 2));
        registerMapping(LVmJungeAmWegesrand);

        // Dialog ausfuehren; ausgefuehrt ? ; Trigger setzt ein; Neuer Dialog mit JungeAmWegesrand

        LVmJungeAmWegesrandOne = new Dialog("JungeAmWegesrandOne", new DialogPart("JungeAmWegesrandOneEins", 6));
        registerMapping(LVmJungeAmWegesrandOne);

        // Wenn vollstaendiges Jojo im Inventar, dann neuer Dialog mit JungeAmWegesrand

        LVmJungeAmWegesrandTwo = new Dialog("JungeAmWegesrandTwo", new DialogPart("JungeAmWegesrandTwo", 1));
        registerMapping(LVmJungeAmWegesrandTwo);

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
