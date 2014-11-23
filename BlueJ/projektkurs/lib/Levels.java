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
     * Die Zurück-Mappings.
     */
    public static final HashMap<Level, String> BACK_MAPPINGS = new HashMap<Level, String>();
    /**
     * Das erste Level.
     */
    public static Level                        level0;
    /**
     * Das zweite Level.
     */
    public static Level                        level1;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Level> MAPPINGS      = new HashMap<String, Level>();

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
        registerMapping("Level0", level0);

        level1 = new Level("Level1", new Spielfeld(25, 25), new Spielfeld(10, 10));
        registerMapping("Level1", level1);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param l
     *            Level
     */
    private static void registerMapping(String name, Level l) {
        MAPPINGS.put(name, l);
        BACK_MAPPINGS.put(l, name);
    }

    /**
     * Konstruktor.
     */
    private Levels() {
    }
}
