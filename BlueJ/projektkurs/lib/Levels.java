package projektkurs.lib;

import java.util.HashMap;

import projektkurs.level.Level;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.world.Spielfeld;

/**
 * Alle Level.
 */
public final class Levels {

    /**
     * Das erste Level.
     */
    public static Level                         level0;
    /**
     * Das zweite Level.
     */
    public static Level                         level1;
    /**
     * Die Mappings.
     */
    private static final HashMap<String, Level> MAPPINGS = new HashMap<String, Level>();

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
     * Initialisiert alle Level.
     */
    @Init(state = State.PRE)
    public static void init() {
        level0 = new Level("Level0", new Spielfeld(100, 100), new Spielfeld(10, 10));
        MAPPINGS.put("Level0", level0);

        level1 = new Level("Level1", new Spielfeld(50, 50));
        MAPPINGS.put("Level1", level1);

    }

    /**
     * Konstruktor.
     */
    private Levels() {
    }
}
