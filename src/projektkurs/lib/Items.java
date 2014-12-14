package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.item.BaseItem;
import projektkurs.item.Item;
import projektkurs.item.ItemHealthPotion;
import projektkurs.item.ItemNuke;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Pair;

/**
 * Alle Items.
 */
public final class Items {

    /**
     * Zurück-Mappings.
     */
    public static final HashMap<Item, String> BACK_MAPPINGS = new HashMap<Item, String>();
    /**
     * Gesundheitstrank.
     */
    public static Item healthPotion;
    /**
     * Item No. 42.
     */
    public static Item item42;
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
        item42 = new BaseItem("42", Images.item42);
        registerItem("42", item42);

        nuke = new ItemNuke();
        registerItem("nuke", nuke);

        key = new BaseItem("key", Images.key);
        registerItem("key", key);

        healthPotion = new ItemHealthPotion();
        registerItem("potion.health", healthPotion);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param item
     *            Item
     */
    private static void registerItem(String name, Item item) {
        MAPPINGS.put(name, item);
        BACK_MAPPINGS.put(item, name);
    }

    /**
     * Nicht instanziierbar.
     */
    private Items() {
    }

}
