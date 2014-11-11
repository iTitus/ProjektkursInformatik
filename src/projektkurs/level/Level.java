package projektkurs.level;

import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;
import projektkurs.world.builder.MapBuilder;

public class Level {

	private Spielfeld currMap;
	private Spielfeld[] maps;

	private String name;

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

	public void GAPallMaps() {
		String temp;
		for (int i = 0; i < maps.length; i++) {
			temp = name;
			temp += "generateAndPopulateMap";
			temp += i;
			maps[i].generateAndPopulateMap(ReflectionUtil.getMethod(
					MapBuilder.class, temp, Spielfeld.class));
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
		if (i < 0 || i >= maps.length)
			throw new IllegalArgumentException();
		return maps[i];
	}

	/**
	 * 
	 * @param i
	 */
	public void setMap(int i) {
		if (i < 0 || i >= maps.length)
			throw new IllegalArgumentException();
		currMap = maps[i];
	}
}
