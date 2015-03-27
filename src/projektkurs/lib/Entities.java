package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityFerry;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityPlayer;
import projektkurs.entity.EntityRedNPC;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;
import projektkurs.world.Spielfeld;

/**
 * Alle Entitytypen.
 */
public final class Entities {

    /**
     * Mappings.
     */
    public static final HashMap<String, Class<? extends Entity>> MAPPINGS = new HashMap<String, Class<? extends Entity>>();

    /**
     * Erstellt einen Entity mithilfe seiner ID.
     *
     * @param id
     *            Entity-ID
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
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.entities", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Entitytypen.
     */
    @Init
    public static void init() {
        registerEntity(EntityPlayer.class);
        registerEntity(EntityItem.class);
        registerEntity(EntityRedNPC.class);
        registerEntity(EntityFerry.class);
    }

    /**
     * Laedt einen Entity aus einem SaveData-Objekt.
     *
     * @param data
     *            SaveData
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
     * @param e
     *            Entity
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
     * @param cls
     *            Entity-Klasse
     */
    private static void registerEntity(Class<? extends Entity> cls) {
        if (cls != null && !MAPPINGS.containsKey(cls.getName())) {
            MAPPINGS.put(cls.getName(), cls);
        } else {
            Logger.warn("Unable to register Entity", cls);
            throw new IllegalArgumentException("Unable to register Entity " + cls);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Entities() {
    }

}
