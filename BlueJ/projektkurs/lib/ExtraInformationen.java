package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projektkurs.io.storage.SaveData;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationChest;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.ExtraInformationFire;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.StringUtil;
import projektkurs.world.Spielfeld;

/**
 * Alle ExtraInformationstypen.
 */
public final class ExtraInformationen {

    public static final Map<Class<? extends ExtraInformation>, String> BACK_MAPPINGS = new HashMap<Class<? extends ExtraInformation>, String>();
    /**
     * Mappings.
     */
    public static final Map<String, Class<? extends ExtraInformation>> MAPPINGS = new HashMap<String, Class<? extends ExtraInformation>>();

    /**
     * Das Pair, das alle ExtraInformationen enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.extras", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle ExtraInformationstypen.
     */
    @Init
    public static void init() {
        registerExtraInformation(ExtraInformationDoor.class, "door");
        registerExtraInformation(ExtraInformationFire.class, "fire");
        registerExtraInformation(ExtraInformationChest.class, "chest");
    }

    /**
     * Laedt einen ExtraInformation aus einem SaveData-Objekt.
     *
     * @param map
     *            Spielfeld
     * @param data
     *            SaveData
     * @return ExtraInformation
     */
    public static ExtraInformation loadExtraInformation(Spielfeld map, SaveData data) {
        ExtraInformation extra = null;
        try {
            extra = ReflectionUtil.newInstance(MAPPINGS.get(data.getString(Strings.EXTRA_ID)), map);
            extra.load(data);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to load ExtraInformation from " + data, t);
        }
        return extra;
    }

    /**
     * Speichert eine ExtraInformation in einer SaveData.
     *
     * @param extra
     *            ExtraInformation
     * @return SaveData
     */
    public static SaveData writeExtraInformation(ExtraInformation extra) {
        SaveData data = new SaveData();
        try {
            data.set(Strings.EXTRA_ID, extra.getInternalName());
            extra.write(data);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to save ExtraInformation '" + extra + "' to SaveData", t);
        }
        return data;
    }

    /**
     * Registriert ein Mapping.
     *
     * @param cls
     *            ExtraInformations-Klasse
     */
    private static void registerExtraInformation(Class<? extends ExtraInformation> cls, String id) {
        if (cls != null && StringUtil.isNotNullOrEmpty(id) && !MAPPINGS.containsKey(id)) {
            MAPPINGS.put(id, cls);
            BACK_MAPPINGS.put(cls, id);
        } else {
            Logger.warn("Unable to register ExtraInformation", cls);
            throw new IllegalArgumentException("Unable to register ExtraInformation " + cls);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private ExtraInformationen() {
    }
}
