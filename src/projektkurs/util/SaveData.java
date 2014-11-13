package projektkurs.util;

import java.io.Serializable;
import java.util.HashMap;

public class SaveData implements Serializable {

	private static final long serialVersionUID = 1L;

	private final HashMap<String, Object> dataMap;

	public SaveData() {
		dataMap = new HashMap<String, Object>();
	}

	public boolean getBoolean(String key) {
		Object o = dataMap.get(key);
		if (o instanceof Boolean)
			return (boolean) o;
		return false;
	}

	public int getInteger(String key) {
		Object o = dataMap.get(key);
		if (o instanceof Integer)
			return (int) o;
		return 0;
	}

	public SaveData getSaveData(String key) {
		Object o = dataMap.get(key);
		if (o instanceof SaveData)
			return (SaveData) o;
		return null;
	}

	public String getString(String key) {
		Object o = dataMap.get(key);
		if (o instanceof String)
			return (String) o;
		return null;
	}

	public boolean hasKey(String key) {
		return dataMap.containsKey(key);
	}

	public void remove(String key) {
		dataMap.remove(key);
	}

	public void set(String key, Object o) {
		if (key != null && o != null && o != this)
			dataMap.put(key, o);
		else
			Logger.warn("Unable to write " + key + " and " + o);
	}

	@Override
	public String toString() {
		String s = "SaveData {";

		String[] keys = dataMap.keySet().toArray(
				new String[dataMap.entrySet().size()]);
		for (int i = 0; i < keys.length; i++) {
			if (i > 0)
				s += ",";
			s += " [" + keys[i] + " = " + dataMap.get(keys[i]).toString() + "]";
		}

		s += " }";
		return s;
	}
}
