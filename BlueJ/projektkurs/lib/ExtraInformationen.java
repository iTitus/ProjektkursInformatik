package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationChest;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.ExtraInformationFire;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;

/**
 * Alle ExtraInformationstypen.
 */
public final class ExtraInformationen {

    /**
     * Mappings.
     */
    public static final HashMap<String, Class<? extends ExtraInformation>> MAPPINGS = new HashMap<String, Class<? extends ExtraInformation>>();

    /**
     * Das Pair, das alle ExtraInformationen enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.extras", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle ExtraInformationstypen.
     */
    @Init
    public static void init() {
        registerExtraInformation(ExtraInformationDoor.class);
        registerExtraInformation(ExtraInformationFire.class);
        registerExtraInformation(ExtraInformationChest.class);
    }

    /**
     * Lädt einen ExtraInformation aus einem SaveData-Objekt.
     *
     * @param data
     *            SaveData
     * @return ExtraInformation
     */
    public static ExtraInformation loadExtraInformation(SaveData data) {

        ExtraInformation extra = ReflectionUtil.newInstance(MAPPINGS.get(data.getString(Strings.EXTRA_ID)));

        try {
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
        data.set(Strings.EXTRA_ID, extra.getInternalName());
        extra.write(data);
        return data;
    }

    /**
     * Registriert ein Mapping.
     *
     * @param cls
     *            ExtraInformations-Klasse
     */
    private static void registerExtraInformation(Class<? extends ExtraInformation> cls) {
        ExtraInformation extra = ReflectionUtil.newInstance(cls);
        if (extra != null && !MAPPINGS.containsKey(extra.getInternalName())) {
            MAPPINGS.put(extra.getInternalName(), cls);
        } else {
            Logger.warn("Unable to register ExtraInformation", cls);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private ExtraInformationen() {
    }
}
