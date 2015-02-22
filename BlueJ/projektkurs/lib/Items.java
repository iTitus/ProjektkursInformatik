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
     * Ohrringe
     */
    public static Item earrings;
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
     * Schluessel.
     */
    public static Item key;
    /**
     * Messer
     */
    public static Item knife;
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
     * Faden
     */
    public static Item thread;

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

        thread = new BaseItem(5, "thread", Sprites.thread);
        registerItem(thread);

        knife = new BaseItem(6, "knife", Sprites.knife);
        registerItem(knife);

        earrings = new BaseItem(7, "earrings", Sprites.earrings);
        registerItem(earrings);

        teddy = new BaseItem(8, "teddy", Sprites.teddy);
        registerItem(teddy);

        stonecatapult = new BaseItem(9, "stonecatapult", Sprites.stonecatapult);
        registerItem(stonecatapult);
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
