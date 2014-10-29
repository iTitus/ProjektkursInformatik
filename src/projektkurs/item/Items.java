package projektkurs.item;

import java.util.HashMap;

import projektkurs.lib.Init;
import projektkurs.lib.Init.State;

public final class Items {

	public static AbstractItem ITEM_42, NUKE, KEY;

	public static final HashMap<String, AbstractItem> MAPPINGS = new HashMap<String, AbstractItem>();

	@Init(state = State.PRE)
	public static void init() {
		ITEM_42 = new BaseItem("42", null);
		MAPPINGS.put("42", ITEM_42);

		NUKE = new BaseItem("nuke", null);
		MAPPINGS.put("nuke", NUKE);

		KEY = new BaseItem("key", null);
		MAPPINGS.put("key", KEY);
	}

	private Items() {
	}

}
