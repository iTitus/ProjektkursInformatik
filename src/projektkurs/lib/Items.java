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
     * Das Kaugummi
     */
    public static Item chewingGum;
    /**
     * Ohrringe
     */
    public static Item earrings;
    /**
     * Das Grammophon
     */
    public static Item gramophoneItem;
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
    public static Item stoneCatapult;
    /**
     * Faden
     */
    public static Item fishnetString;
    /**
     * Der Koffer
     */
    public static Item suitCase;
    /**
     * Teddybaer
     */
    public static Item teddydefault;

    public static Item teddyWithEarrings;

    public static Item teddyWithFishnetString;

    public static Item fishnet;

    public static Item stone;

    public static Item teddyVoodoo;

    public static Item stoneCatapultwithStone;
    /**
     * Das Jojo
     */
    public static Item yoyoFixed;

    public static Item yoyoBroken;

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

        fishnetString = new BaseItem(5, "fishnetString", Sprites.fishnetString);
        registerItem(fishnetString);

        knife = new BaseItem(6, "knife", Sprites.knife);
        registerItem(knife);

        earrings = new BaseItem(7, "earrings", Sprites.earrings);
        registerItem(earrings);

        teddydefault = new BaseItem(8, "teddydefault", Sprites.teddydefault);
        registerItem(teddydefault);

        stoneCatapult = new BaseItem(9, "stoneCatapult", Sprites.stoneCatapult);
        registerItem(stoneCatapult);

        yoyoFixed = new BaseItem(10, "yoyoFixed", Sprites.yoyoFixed);
        registerItem(yoyoFixed);

        suitCase = new BaseItem(11, "suitCase", Sprites.suitCase);
        registerItem(suitCase);

        gramophoneItem = new BaseItem(12, "gramophoneItem", Sprites.gramophoneItem);
        registerItem(gramophoneItem);

        chewingGum = new BaseItem(13, "chewingGum", Sprites.chewingGum);
        registerItem(chewingGum);

        cards = new BaseItem(14, "cards", Sprites.cards);
        registerItem(cards);

        teddyWithEarrings = new BaseItem(15, "teddyWithEarrings", Sprites.teddyWithEarrings);
        registerItem(teddyWithEarrings);

        teddyWithFishnetString = new BaseItem(16, "teddyWithFishnetString", Sprites.teddyWithFishnetString);
        registerItem(teddyWithFishnetString);

        fishnet = new BaseItem(17, "fishnet", Sprites.fishnet);
        registerItem(fishnet);

        stone = new BaseItem(18, "stone", Sprites.stone);
        registerItem(stone);

        teddyVoodoo = new BaseItem(19, "teddyVoodoo", Sprites.teddyVoodoo);
        registerItem(teddyVoodoo);

        stoneCatapultwithStone = new BaseItem(20, "stoneCatapultwithStone", Sprites.stoneCatapultwithStone);
        registerItem(stoneCatapultwithStone);

        yoyoBroken = new BaseItem(21, "yoyoBroken", Sprites.yoyoBroken);
        registerItem(yoyoBroken);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param i
     *            Item
     */
    private static void registerItem(Item i) {
        if (i != null && !MAPPINGS.containsKey(i.getInternalName()) && i.getID() > 0 && i.getID() < ITEMS.length && ITEMS[i.getID()] == null) {
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
