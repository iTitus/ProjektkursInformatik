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
import projektkurs.entity.EntityWindmill;
import projektkurs.entity.EntityWitchCauldron;
import projektkurs.entity.Entityhouse_1_4x3;
import projektkurs.entity.Entityhouse_2_4x3;
import projektkurs.entity.Entityhouse_2b_3x4;
import projektkurs.entity.Entityhouse_3_4x3;
import projektkurs.entity.Entityhouse_3b_3x4;
import projektkurs.entity.Entityhouse_4_4x3;
import projektkurs.entity.Entityhouse_4b_3x4;
import projektkurs.io.storage.SaveData;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.StringUtil;
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
        registerEntity(Entityhouse_1_4x3.class, "house_1_4x3");
        registerEntity(Entityhouse_2_4x3.class, "house_2_4x3");
        registerEntity(Entityhouse_3_4x3.class, "house_3_4x3");
        registerEntity(Entityhouse_3b_3x4.class, "house_3b_3x4");
        registerEntity(Entityhouse_4_4x3.class, "house_4_4x4");
        registerEntity(Entityhouse_4b_3x4.class, "house_4b_4x4");
        registerEntity(Entityhouse_2b_3x4.class, "house_2b_3x4");
        registerEntity(EntityWindmill.class, "windmill");



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
    
    public static boolean isRegistered(Entity e) {
        return e != null && BACK_MAPPINGS.containsKey(e.getClass());
    }

    /**
     * Registriert ein Mapping.
     *
     * @param cls
     *            Entity-Klasse
     */
    private static void registerEntity(Class<? extends Entity> cls, String id) {
        if (cls != null && StringUtil.isNotNullOrEmpty(id) && !MAPPINGS.containsKey(id)) {
            MAPPINGS.put(id, cls);
            BACK_MAPPINGS.put(cls, id);
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
