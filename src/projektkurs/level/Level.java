package projektkurs.level;

import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;
import projektkurs.world.builder.MapBuilder;

/**
 * Ein Level
 */
public class Level {

	private Spielfeld currMap;
	private final Spielfeld[] maps;
	private final String name;

	/**
	 * 
	 * @param storymanager
	 * @param maps
	 */
	public Level(String name, Spielfeld... maps) {
		this.maps = maps;
		currMap = maps[0];
		this.name = name;
	}

	public void generateAndPopulateAll() {
		String methodName;
		for (int i = 0; i < maps.length; i++) {
			methodName = name;
			methodName += "generateAndPopulateMap";
			methodName += i;
			ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(
					MapBuilder.class, methodName, Spielfeld.class), maps[i]);
		}
	}

	/**
	 * 
	 * @return current Map
	 */
	public Spielfeld getCurrMap() {
		return currMap;
	}

	/**
	 * 
	 * @param i
	 * @return Map at i
	 */
	public Spielfeld getMapAt(int i) {
		if (i < 0 || i >= maps.length) {
			Logger.warn("Unable to get map");
			return null;
		}
		return maps[i];
	}

	/**
	 * 
	 * @param i
	 */
	public void setMap(int i) {
		if (i < 0 || i >= maps.length) {
			Logger.warn("Unable to set map");
			return;
		}
		currMap = maps[i];
	}
}
