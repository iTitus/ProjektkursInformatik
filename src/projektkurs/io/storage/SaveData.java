package projektkurs.io.storage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import projektkurs.util.Logger;

/**
 * Eine SaveData.
 */
@XmlRootElement
public final class SaveData implements Serializable {

    /**
     * Version, wird zum (De-)Serialisieren verwendet.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Interne HashMap.
     */
    private Map<String, Object> dataMap;

    /**
     * Konstruktor.
     */
    public SaveData() {
        dataMap = new HashMap<String, Object>();
    }

    /**
     * Gibt den Boolean zurueck, der mit dem gegebenen Key verbunden ist.
     *
     * @param key
     *            Key
     * @return boolean
     */
    public boolean getBoolean(String key) {
        Object o = dataMap.get(key);
        if (o instanceof Boolean) {
            return (boolean) o;
        }
        return false;
    }

    /**
     * Gibt den Integer zurueck, der mit dem gegebenen Key verbunden ist.
     *
     * @param key
     *            Key
     * @return int
     */
    public int getInteger(String key) {
        Object o = dataMap.get(key);
        if (o instanceof Integer) {
            return (int) o;
        }
        return 0;
    }

    /**
     * Gibt die SaveData zurueck, die mit dem gegebenen Key verbunden ist.
     *
     * @param key
     *            Key
     * @return SaveData
     */
    public SaveData getSaveData(String key) {
        Object o = dataMap.get(key);
        if (o instanceof SaveData) {
            return (SaveData) o;
        }
        return null;
    }

    /**
     * Gibt den String zurueck, der mit dem gegebenen Key verbunden ist.
     *
     * @param key
     *            Key
     * @return String
     */
    public String getString(String key) {
        Object o = dataMap.get(key);
        if (o instanceof String) {
            return (String) o;
        }
        return null;
    }

    /**
     * Ist der Key vorhanden.
     *
     * @param key
     *            Key
     * @return true, wenn ja; false, wenn nein
     */
    public boolean hasKey(String key) {
        return dataMap.containsKey(key);
    }

    /**
     * Entfernt einen Key zusammen mit seinem Objekt.
     *
     * @param key
     *            Key
     */
    public void remove(String key) {
        dataMap.remove(key);
    }

    /**
     * Setzt einen Key mit einem Objekt.
     *
     * @param key
     *            Key
     * @param o
     *            Objekt
     */
    public void set(String key, Object o) {
        if (key != null && o != null && o != this) {
            dataMap.put(key, o);
        } else {
            Logger.warn("Unable to set '" + key + "' -> '" + o + "'");
        }
    }

    @Override
    public String toString() {
        String s = "SaveData {";

        String[] keys = dataMap.keySet().toArray(new String[dataMap.entrySet().size()]);
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) {
                s += ",";
            }
            s += " [" + keys[i] + " = " + dataMap.get(keys[i]).toString() + "]";
        }

        s += " }";
        return s;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
