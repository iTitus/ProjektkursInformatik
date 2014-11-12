package projektkurs.item;

import java.util.HashMap;

import projektkurs.lib.Images;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

public final class Items {

	public static AbstractItem item_42, nuke, key, healthpotion;
	public static final HashMap<String, AbstractItem> MAPPINGS = new HashMap<String, AbstractItem>();

	@Init(state = State.PRE)
	public static void init() {
		item_42 = new BaseItem("42", Images.item_42);
		MAPPINGS.put("42", item_42);

		nuke = new ItemNuke();
		MAPPINGS.put("nuke", nuke);

		key = new BaseItem("key", Images.key);
		MAPPINGS.put("key", key);

		healthpotion = new ItemHealthPotion(100);
		MAPPINGS.put("healthpotion", healthpotion);
	}

	private Items() {
	}

}
