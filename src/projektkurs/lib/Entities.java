package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityPlayer;
import projektkurs.entity.EntityRedNPC;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;

/**
 * Alle Entitytypen.
 */
public final class Entities {

    /**
     * Mappings.
     */
    public static final HashMap<String, Class<? extends Entity>> MAPPINGS = new HashMap<String, Class<? extends Entity>>();

    /**
     * Erstellt einen Entity seiner ID.
     *
     * @param id
     *            Entity-ID
     * @return Entity
     */
    public static Entity createEntity(String id) {

        if (id == null || id.equalsIgnoreCase("")) {
            return null;
        }

        return ReflectionUtil.newInstance(MAPPINGS.get(id));
    }

    /**
     * Das Pair, das alle Entities enthält.
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
    }

    /**
     * Lädt einen Entity aus einem SaveData-Objekt.
     *
     * @param data
     *            SaveData
     * @return Entity
     */
    public static Entity loadEntity(SaveData data) {

        if (data == null) {
            return null;
        }

        Entity e = ReflectionUtil.newInstance(MAPPINGS.get(data.getString(Strings.ENTITY_ID)));

        try {
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
        if (e == null) {
            return null;
        }
        SaveData data = new SaveData();
        data.set(Strings.ENTITY_ID, e.getInternalName());
        e.write(data);
        return data;
    }

    /**
     * Registriert ein Mapping.
     *
     * @param cls
     *            Entity-Klasse
     */
    private static void registerEntity(Class<? extends Entity> cls) {
        Entity e = ReflectionUtil.newInstance(cls);
        if (e != null && !MAPPINGS.containsKey(e.getInternalName())) {
            MAPPINGS.put(e.getInternalName(), cls);
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
