package projektkurs.item;

import java.util.HashMap;

import projektkurs.lib.Images;
import projektkurs.util.Init;

public final class Items {

	public static AbstractItem ITEM_42, NUKE, KEY;

	public static final HashMap<String, AbstractItem> MAPPINGS = new HashMap<String, AbstractItem>();

	@Init
	public static void init() {
		ITEM_42 = new BaseItem("42", Images.item_42);
		MAPPINGS.put("42", ITEM_42);

		NUKE = new BaseItem("nuke", Images.nuke);
		MAPPINGS.put("nuke", NUKE);

		KEY = new BaseItem("key", Images.key);
		MAPPINGS.put("key", KEY);
	}

	private Items() {
	}

}
