package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.render.SpriteSheet;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle SpriteSheets.
 */
public final class SpriteSheets {

    public static SpriteSheet entities;
    public static SpriteSheet guis;
    public static SpriteSheet items;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, SpriteSheet> MAPPINGS = new HashMap<String, SpriteSheet>();
    public static SpriteSheet raster;

    /**
     * Das Pair, das alle SpriteSheets enthaelt.
     * @return Pair
     */
    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.spritesheets", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle SpriteSheets.
     */
    @Init(State.RESOURCES)
    public static void init() {
        raster = new SpriteSheet("raster", "rasterSheet.png");
        registerSpriteSheet(raster);

        items = new SpriteSheet("items", "itemSheet.png");
        registerSpriteSheet(items);

        guis = new SpriteSheet("guis", "guiSheet.png");
        registerSpriteSheet(guis);

        entities = new SpriteSheet("entities", "entitySheet.png");
        registerSpriteSheet(entities);

    }

    /**
     * Registriert ein Mapping.
     * @param s
     * SpriteSheet
     */
    public static void registerSpriteSheet(SpriteSheet s) {
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
