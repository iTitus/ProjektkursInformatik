package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.level.Level;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;
import projektkurs.world.Spielfeld;

/**
 * Alle Level.
 */
public final class Levels {

    /**
     * Die Zurueck-Mappings.
     */
    public static final HashMap<Level, String> BACK_MAPPINGS = new HashMap<Level, String>();
    /**
     * Das erste Level.
     */
    public static Level level0;
    /**
     * Das zweite Level.
     */
    public static Level level1;
    /**
     * Die Mappings.
     */
    public static Level level2;

    public static final HashMap<String, Level> MAPPINGS = new HashMap<String, Level>();

    /**
     * Generiert alle Level.
     */
    public static void generateAndPopulateAllLevels() {
        for (Level l : MAPPINGS.values()) {
            l.generateAndPopulateAll();
        }
    }

    /**
     * Das Pair, das alle Levels enthaelt.
     * @return Pair
     */
    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.levels", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Level.
     */
    @Init(State.PRE)
    public static void init() {
        level0 = new Level("Level0");
        level0.addMap(new Spielfeld(level0, 100, 100, MathUtil.floorDiv(Integers.sightX, 2), MathUtil.floorDiv(Integers.sightY, 2)));
        level0.addMap(new Spielfeld(level0, 20, 10, 1, 1));
        registerMapping(level0);

        level1 = new Level("Level1");
        level1.addMap(new Spielfeld(level1, 90, 70, 10, 26));
        level1.addMap(new Spielfeld(level1, 70, 70, 10, 0));
        registerMapping(level1);

        level2 = new Level("Level2");
        // 80 59
        level2.addMap(new Spielfeld(level2, 100, 100, 80, 62));
        level2.addMap(new Spielfeld(level2, 30, 30, 15, 28));
        level2.addMap(new Spielfeld(level2, 30, 20, 1, 10));
        level2.addMap(new Spielfeld(level2, 68, 70, 41, 68));
        // 50 98
        level2.addMap(new Spielfeld(level2, 100, 100, 18, 79));
        registerMapping(level2);

    }

    /**
     * Registriert ein Mapping.
     * @param l
     * Level
     */
    private static void registerMapping(Level l) {
        if (l != null && !MAPPINGS.containsKey(l.getName())) {
            MAPPINGS.put(l.getName(), l);
        } else {
            Logger.warn("Unable to register Level", l);
            throw new IllegalArgumentException("Unable to register Level " + l);
        }
    }

    /**
     * Konstruktor.
     */
    private Levels() {
    }
}
