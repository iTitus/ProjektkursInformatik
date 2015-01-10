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
     * Schlüssel.
     */
    public static Item key;
    /**
     * Mappings.
     */
    public static final HashMap<String, Item> MAPPINGS = new HashMap<String, Item>();
    /**
     * Atombombe.
     */
    public static Item nuke;

    /**
     * Das Pair, das alle Items enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.items", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Items.
     */
    @Init(state = State.PRE)
    public static void init() {
        item42 = new BaseItem(1, "42", Images.item42);
        registerItem(item42);

        nuke = new ItemNuke(2);
        registerItem(nuke);

        key = new BaseItem(3, "key", Images.key);
        registerItem(key);

        healthPotion = new ItemHealthPotion(4);
        registerItem(healthPotion);
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
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Items() {
    }

}
