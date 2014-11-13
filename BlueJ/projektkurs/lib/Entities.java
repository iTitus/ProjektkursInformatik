package projektkurs.lib;

import java.util.HashMap;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityPlayer;
import projektkurs.entity.EntityRedNPC;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;

public final class Entities {

	public static final HashMap<Class<? extends Entity>, String> BACK_MAPPINGS = new HashMap<Class<? extends Entity>, String>();
	public static final HashMap<String, Class<? extends Entity>> MAPPINGS = new HashMap<String, Class<? extends Entity>>();

	@Init
	public static void init() {
		registerEntity("player", EntityPlayer.class);
		registerEntity("item", EntityItem.class);
		registerEntity("redNPC", EntityRedNPC.class);
	}

	public static Entity loadEntity(SaveData data) {

		Entity e = ReflectionUtil.newInstance(MAPPINGS.get(data
				.getString(Strings.ENTITY_ID)));

		try {
			e.load(data);
		} catch (Throwable t) {
			Logger.warn("Unable to load Entity from " + data, t);
		}
		return e;
	}

	public static SaveData writeEntity(Entity e) {
		SaveData data = new SaveData();
		data.set(Strings.ENTITY_ID, BACK_MAPPINGS.get(e.getClass()));
		e.write(data);
		return data;
	}

	private static void registerEntity(String name, Class<? extends Entity> cls) {
		MAPPINGS.put(name, cls);
		BACK_MAPPINGS.put(cls, name);
	}

	private Entities() {
	}

}
