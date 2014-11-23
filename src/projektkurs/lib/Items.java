package projektkurs.lib;

import java.util.HashMap;

import projektkurs.item.AbstractItem;
import projektkurs.item.BaseItem;
import projektkurs.item.ItemHealthPotion;
import projektkurs.item.ItemNuke;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

/**
 * Alle Items.
 */
public final class Items {

  /**
   * Zurück-Mappings.
   */
  public static final HashMap<AbstractItem, String> BACK_MAPPINGS = new HashMap<AbstractItem, String>();
  /**
   * Gesundheitstrank.
   */
  public static AbstractItem                        healthPotion;
  /**
   * Item No. 42.
   */
  public static AbstractItem                        item42;
  /**
   * Schlüssel.
   */
  public static AbstractItem                        key;
  /**
   * Mappings.
   */
  public static final HashMap<String, AbstractItem> MAPPINGS      = new HashMap<String, AbstractItem>();
  /**
   * Atombombe.
   */
  public static AbstractItem                        nuke;

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
   *          Name
   * @param item
   *          Item
   */
  private static void registerItem(String name, AbstractItem item) {
    MAPPINGS.put(name, item);
    BACK_MAPPINGS.put(item, name);
  }

  /**
   * Nicht instanziierbar.
   */
  private Items() {
  }

}
