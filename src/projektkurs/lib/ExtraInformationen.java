package projektkurs.lib;

import java.util.HashMap;

import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.SaveData;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.ExtraInformationFire;

public final class ExtraInformationen {

	public static final HashMap<Class<? extends ExtraInformation>, String> BACK_MAPPINGS = new HashMap<Class<? extends ExtraInformation>, String>();
	public static final HashMap<String, Class<? extends ExtraInformation>> MAPPINGS = new HashMap<String, Class<? extends ExtraInformation>>();

	@Init
	public static void init() {
		registerExtraInformation("door", ExtraInformationDoor.class);
		registerExtraInformation("fire", ExtraInformationFire.class);
		registerExtraInformation("kiste", ExtraInformationFire.class);
	}

	public static ExtraInformation loadExtraInformation(SaveData data) {

		ExtraInformation extra = ReflectionUtil.newInstance(MAPPINGS.get(data
				.getString(Strings.EXTRA_ID)));

		try {
			extra.load(data);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to load ExtraInformation from " + data,
					t);
		}
		return extra;
	}

	public static SaveData writeExtraInformation(ExtraInformation extra) {
		SaveData data = new SaveData();
		data.set(Strings.EXTRA_ID,
				ExtraInformationen.BACK_MAPPINGS.get(extra.getClass()));
		extra.write(data);
		return data;
	}

	private static void registerExtraInformation(String name,
			Class<? extends ExtraInformation> cls) {
		MAPPINGS.put(name, cls);
		BACK_MAPPINGS.put(cls, name);
	}

	private ExtraInformationen() {
	}
}
