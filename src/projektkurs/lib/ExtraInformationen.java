package projektkurs.lib;

import java.util.HashMap;

import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.ExtraInformationFire;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;

/**
 * Alle ExtraInformationstypen.
 */
public final class ExtraInformationen {

    /**
     * Zurück-Mappings.
     */
    public static final HashMap<Class<? extends ExtraInformation>, String> BACK_MAPPINGS = new HashMap<Class<? extends ExtraInformation>, String>();
    /**
     * Mappings.
     */
    public static final HashMap<String, Class<? extends ExtraInformation>> MAPPINGS = new HashMap<String, Class<? extends ExtraInformation>>();

    /**
     * Initialisiert alle ExtraInformationstypen.
     */
    @Init
    public static void init() {
        registerExtraInformation("door", ExtraInformationDoor.class);
        registerExtraInformation("fire", ExtraInformationFire.class);
        registerExtraInformation("kiste", ExtraInformationFire.class);
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
        data.set(Strings.EXTRA_ID, ExtraInformationen.BACK_MAPPINGS.get(extra.getClass()));
        extra.write(data);
        return data;
    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param cls
     *            Entity-Klasse
     */
    private static void registerExtraInformation(String name, Class<? extends ExtraInformation> cls) {
        MAPPINGS.put(name, cls);
        BACK_MAPPINGS.put(cls, name);
    }

    /**
     * Nicht instanziierbar.
     */
    private ExtraInformationen() {
    }
}
