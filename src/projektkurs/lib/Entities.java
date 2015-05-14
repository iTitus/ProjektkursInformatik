package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityBoomBarrier;
import projektkurs.entity.EntityBoomBarrierOpen;
import projektkurs.entity.EntityBoy;
import projektkurs.entity.EntityFerry;
import projektkurs.entity.EntityFerryhouse;
import projektkurs.entity.EntityFerryman;
import projektkurs.entity.EntityFisher;
import projektkurs.entity.EntityFisherboat;
import projektkurs.entity.EntityGramophone;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNest;
import projektkurs.entity.EntityPlayer;
import projektkurs.entity.EntityRedNPC;
import projektkurs.entity.EntityTest;
import projektkurs.entity.EntityTrashCan;
import projektkurs.entity.EntityVilleCar;
import projektkurs.entity.EntityWitchCauldron;
import projektkurs.io.storage.SaveData;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Alle Entitytypen.
 */
public final class Entities {

	public static final HashMap<Class<? extends Entity>, String> BACK_MAPPINGS = new HashMap<Class<? extends Entity>, String>();
	/**
	 * Mappings.
	 */
	public static final HashMap<String, Class<? extends Entity>> MAPPINGS = new HashMap<String, Class<? extends Entity>>();

	/**
	 * Nicht instanziierbar.
	 */
	private Entities() {
	}

	/**
	 * Erstellt einen Entity mithilfe seiner ID.
	 *
	 * @param id Entity-ID
	 * @return Entity
	 */
	public static Entity createEntity(Spielfeld map, String id) {

		if (id == null || id.length() <= 0) {
			return null;
		}

		return ReflectionUtil.newInstance(MAPPINGS.get(id), map);
	}

	/**
	 * Das Pair, das alle Entities enthaelt.
	 *
	 * @return Pair
	 */
	public static Pair<String, List<String>> getPair() {
		return new Pair<String, List<String>>("info.entities", new ArrayList<String>(MAPPINGS.keySet()));
	}

	/**
	 * Initialisiert alle Entitytypen.
	 */
	@Init
	public static void init() {
		registerEntity(EntityPlayer.class, "player");
		registerEntity(EntityItem.class, "item");
		registerEntity(EntityRedNPC.class, "redNPC");
		registerEntity(EntityFerry.class, "ferry");
		registerEntity(EntityFerryhouse.class, "ferryHouse");
		registerEntity(EntityFerryman.class, "ferryMan");
		registerEntity(EntityFisher.class, "fisher");
		registerEntity(EntityFisherboat.class, "fisherBoat");
		registerEntity(EntityGramophone.class, "gramophone");
		registerEntity(EntityBoy.class, "boy");
		registerEntity(EntityNest.class, "nest");
		registerEntity(EntityBoomBarrier.class, "boomBarrier");
		registerEntity(EntityBoomBarrierOpen.class, "boomBarrierOpen");
		registerEntity(EntityTrashCan.class, "trashCan");
		registerEntity(EntityVilleCar.class, "villeCar");
		registerEntity(EntityTest.class, "test");
		registerEntity(EntityWitchCauldron.class, "witchCauldron");
	}

	/**
	 * Laedt einen Entity aus einem SaveData-Objekt.
	 *
	 * @param data SaveData
	 * @return Entity
	 */
	public static Entity loadEntity(Spielfeld map, SaveData data) {
		Entity e = null;
		try {
			e = ReflectionUtil.newInstance(MAPPINGS.get(data.getString(Strings.ENTITY_ID)), map);
			e.load(data);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to load Entity from " + data, t);
		}
		return e;
	}

	/**
	 * Speichert einen Entity in einer SaveData.
	 *
	 * @param e Entity
	 * @return SaveData
	 */
	public static SaveData writeEntity(Entity e) {
		SaveData data = new SaveData();
		try {
			data.set(Strings.ENTITY_ID, e.getInternalName());
			e.write(data);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to save Entity '" + e + "' to SaveData", t);
		}
		return data;
	}

	/**
	 * Registriert ein Mapping.
	 *
	 * @param cls Entity-Klasse
	 */
	private static void registerEntity(Class<? extends Entity> cls, String name) {
		if (cls != null && !MAPPINGS.containsKey(cls.getName())) {
			MAPPINGS.put(name, cls);
			BACK_MAPPINGS.put(cls, name);
		} else {
			Logger.warn("Unable to register Entity", cls);
			throw new IllegalArgumentException("Unable to register Entity " + cls);
		}
	}

}
