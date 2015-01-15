package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.render.SpriteSheet;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle SpriteSheets.
 */
public final class SpriteSheets {

    /**
     * Die Mappings.
     */
    public static final HashMap<String, SpriteSheet> MAPPINGS = new HashMap<String, SpriteSheet>();
    public static SpriteSheet raster;

    /**
     * Das Pair, das alle SpriteSheets enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.spritesheets", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle SpriteSheets.
     */
    @Init(state = State.RESOURCES_PRE)
    public static void init() {
        raster = new SpriteSheet("raster", "rasterSheet.png");
        registerSpriteSheet(raster);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param s
     *            SpriteSheet
     */
    private static void registerSpriteSheet(SpriteSheet s) {
        if (s != null && !MAPPINGS.containsKey(s.getName())) {
            MAPPINGS.put(s.getName(), s);
        } else {
            Logger.warn("Unable to register SpriteSheet", s);
            throw new IllegalArgumentException("Unable to register SpriteSheet " + s);
        }
    }

    private SpriteSheets() {
    }

}
