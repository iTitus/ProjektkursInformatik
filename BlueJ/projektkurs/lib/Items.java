package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.item.BaseItem;
import projektkurs.item.Item;
import projektkurs.item.ItemHealthPotion;
import projektkurs.item.ItemNuke;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Items.
 */
public final class Items {

    /**
     * Die Karten
     */
    public static Item cards;
    /**
     * Ohrringe
     */
    public static Item earrings;
    /**
     * Faden
     */
    public static Item faden;
    /**
     * Das Grammophon
     */
    public static Item grammophon;
    /**
     * Gesundheitstrank.
     */
    public static Item healthPotion;
    /**
     * Item No. 42.
     */
    public static Item item42;
    /**
     * Array aller Items nach ihrer ID.
     */
    public static final Item[] ITEMS = new Item[Integers.MAX_ITEM_NUMBER];
    /**
     * Das Jojo
     */
    public static Item jojo;
    /**
     * Das Kaugummi
     */
    public static Item kaugummi;
    /**
     * Schluessel.
     */
    public static Item key;
    /**
     * Messer
     */
    public static Item knife;
    /**
     * Der Koffer
     */
    public static Item koffer;
    /**
     * Mappings.
     */
    public static final HashMap<String, Item> MAPPINGS = new HashMap<String, Item>();
    /**
     * Atombombe.
     */
    public static Item nuke;
    /**
     * Steinschleuder
     */
    public static Item stonecatapult;
    /**
     * Teddybaer
     */
    public static Item teddy;

    /**
     * Das Pair, das alle Items enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.items", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Items.
     */
    @Init(State.PRE)
    public static void init() {
        item42 = new BaseItem(1, "42", Sprites.item42);
        registerItem(item42);

        nuke = new ItemNuke(2);
        registerItem(nuke);

        key = new BaseItem(3, "key", Sprites.key);
        registerItem(key);

        healthPotion = new ItemHealthPotion(4);
        registerItem(healthPotion);

        faden = new BaseItem(5, "faden", Sprites.faden);
        registerItem(faden);

        knife = new BaseItem(6, "knife", Sprites.knife);
        registerItem(knife);

        earrings = new BaseItem(7, "earrings", Sprites.earrings);
        registerItem(earrings);

        teddy = new BaseItem(8, "teddy", Sprites.teddy);
        registerItem(teddy);

        stonecatapult = new BaseItem(9, "stonecatapult", Sprites.stonecatapult);
        registerItem(stonecatapult);

        jojo = new BaseItem(10, "jojo", Sprites.jojo);
        registerItem(jojo);

        koffer = new BaseItem(11, "koffer", Sprites.koffer);
        registerItem(koffer);

        grammophon = new BaseItem(12, "grammophon", Sprites.grammophon);
        registerItem(grammophon);

        kaugummi = new BaseItem(13, "kaugummi", Sprites.kaugummi);
        registerItem(kaugummi);

        cards = new BaseItem(14, "cards", Sprites.cards);
        registerItem(cards);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param i
     *            Item
     */
    private static void registerItem(Item i) {
        if (i != null && !MAPPINGS.containsKey(i.getName()) && i.getID() > 0 && i.getID() < ITEMS.length && ITEMS[i.getID()] == null) {
            MAPPINGS.put(i.getInternalName(), i);
            ITEMS[i.getID()] = i;
        } else {
            Logger.warn("Unable to register Item", i);
            throw new IllegalArgumentException("Unable to register Item " + i);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Items() {
    }

}
