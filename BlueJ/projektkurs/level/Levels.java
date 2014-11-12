package projektkurs.level;

import java.util.HashMap;

import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.world.Spielfeld;

public class Levels {

	private static final HashMap<String, Level> MAPPINGS = new HashMap<String, Level>();
	public static Level level1, level2;

	@Init(state = State.PRE)
	public static void init() {
		level1 = new Level("Level1", new Spielfeld(100, 100), new Spielfeld(10,
				10));
		MAPPINGS.put("Level1", level1);

		level2 = new Level("Level2", new Spielfeld(50, 50));
		MAPPINGS.put("Level2", level2);

	}

	@Init
	public static void generateAndPopulateAllLevels() {
		for (Level l : MAPPINGS.values())
			l.generateAndPopulateAll();
	}
}
