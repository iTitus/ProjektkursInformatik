package projektkurs.lib;

import java.util.HashMap;

import projektkurs.item.AbstractItem;
import projektkurs.item.BaseItem;
import projektkurs.item.ItemHealthPotion;
import projektkurs.item.ItemNuke;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

public final class Items {

    public static final HashMap<AbstractItem, String> BACK_MAPPINGS = new HashMap<AbstractItem, String>();
    public static AbstractItem                        item_42, nuke, key, healthPotion;
    public static final HashMap<String, AbstractItem> MAPPINGS      = new HashMap<String, AbstractItem>();

    @Init(state = State.PRE)
    public static void init() {
        item_42 = new BaseItem("42", Images.item42);
        registerItem("42", item_42);

        nuke = new ItemNuke();
        registerItem("nuke", nuke);

        key = new BaseItem("key", Images.key);
        registerItem("key", key);

        healthPotion = new ItemHealthPotion(100);
        registerItem("potion.health", healthPotion);
    }

    private static void registerItem(String name, AbstractItem i) {
        MAPPINGS.put(name, i);
        BACK_MAPPINGS.put(i, name);
    }

    private Items() {
    }

}
