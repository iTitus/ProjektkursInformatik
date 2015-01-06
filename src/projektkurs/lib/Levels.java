package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

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
     * Die Zurück-Mappings.
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
    public static final HashMap<String, Level> MAPPINGS = new HashMap<String, Level>();

    /**
     * Generiert alle Level.
     */
    @Init
    public static void generateAndPopulateAllLevels() {
        for (Level l : MAPPINGS.values()) {
            l.generateAndPopulateAll();
        }
    }

    /**
     * Das Pair, das alle Kommandos enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.levels", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Level.
     */
    @Init(state = State.PRE)
    public static void init() {
        level0 = new Level("Level0", new Spielfeld(100, 100, MathUtil.floorDiv(Integers.sightX, 2), MathUtil.floorDiv(Integers.sightY, 2)), new Spielfeld(20, 10, 1, 1));
        registerMapping(level0);

        level1 = new Level("Level1", new Spielfeld(25, 25, 1, 1), new Spielfeld(10, 10, 1, 1));
        registerMapping(level1);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param l
     *            Level
     */
    private static void registerMapping(Level l) {
        if (l != null && !MAPPINGS.containsKey(l.getName())) {
            MAPPINGS.put(l.getName(), l);
        } else {
            Logger.warn("Unable to register Level", l);
        }
    }

    /**
     * Konstruktor.
     */
    private Levels() {
    }
}
