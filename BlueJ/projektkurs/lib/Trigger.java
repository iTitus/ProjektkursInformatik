package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projektkurs.io.storage.SaveData;
import projektkurs.story.trigger.AbstractTrigger;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.CombinedAndTrigger;
import projektkurs.story.trigger.CombinedOrTrigger;
import projektkurs.story.trigger.DialogTrigger;
import projektkurs.story.trigger.InventoryHasItemStackTrigger;
import projektkurs.story.trigger.InvertedTrigger;
import projektkurs.story.trigger.PosTrigger;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.StringUtil;

public class Trigger {

	public static final Map<Class<? extends AbstractTrigger>, String> BACK_MAPPINGS = new HashMap<Class<? extends AbstractTrigger>, String>();
	public static final Map<String, Class<? extends AbstractTrigger>> MAPPINGS = new HashMap<String, Class<? extends AbstractTrigger>>();

	private Trigger() {
	}

	public static AbstractTrigger createTrigger(String id) {

		if (StringUtil.isNullOrEmpty(id)) {
			return null;
		}

		return ReflectionUtil.newInstance(MAPPINGS.get(id));
	}

	public static Pair<String, List<String>> getPair() {
		return new Pair<String, List<String>>("info.trigger", new ArrayList<String>(MAPPINGS.keySet()));
	}

	@Init
	public static void init() {
		registerTrigger(AreaTrigger.class, "area");
		registerTrigger(CombinedAndTrigger.class, "combinedAND");
		registerTrigger(CombinedOrTrigger.class, "combinedOR");
		registerTrigger(DialogTrigger.class, "dialog");
		registerTrigger(InventoryHasItemStackTrigger.class, "inventoryHasItemStack");
		registerTrigger(InvertedTrigger.class, "NOT");
		registerTrigger(PosTrigger.class, "position");
	}

	public static AbstractTrigger loadTrigger(SaveData data) {
		AbstractTrigger trigger = null;
		try {
			trigger = ReflectionUtil.newInstance(MAPPINGS.get(data.getString("id")));
			trigger.load(data);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to load Trigger from " + data, t);
		}
		return trigger;
	}

	public static SaveData writeTrigger(AbstractTrigger trigger) {
		SaveData data = new SaveData();
		try {
			data.set("id", trigger.getInternalName());
			trigger.write(data);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to save Trigger '" + trigger + "' to SaveData", t);
		}
		return data;
	}

	private static void registerTrigger(Class<? extends AbstractTrigger> cls, String id) {
		if (cls != null && StringUtil.isNotNullOrEmpty(id) && !MAPPINGS.containsKey(id)) {
			MAPPINGS.put(id, cls);
			BACK_MAPPINGS.put(cls, id);
		} else {
			Logger.warn("Unable to register Trigger", cls);
			throw new IllegalArgumentException("Unable to register Trigger " + cls);
		}
	}
}
