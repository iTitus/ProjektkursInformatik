package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.render.Sprite;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Sprites.
 */
public final class Sprites {

    public static Sprite chest;
    public static Sprite destroyed;
    public static Sprite door;
    public static Sprite door_open;
    public static Sprite finish;
    public static Sprite[] fire;
    public static Sprite grass;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Sprite> MAPPINGS = new HashMap<String, Sprite>();
    public static Sprite tree;
    public static Sprite wall;
    public static Sprite water;

    /**
     * Das Pair, das alle Sprites enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.sprites", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Sprites.
     */
    @Init(state = State.RESOURCES)
    public static void init() {
        wall = new Sprite("wall", Integers.RASTER_SIZE, 0, 0, SpriteSheets.raster);
        registerSprite(wall);

        grass = new Sprite("grass", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(grass);

        tree = new Sprite("tree", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree);

        chest = new Sprite("chest", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(chest);

        door = new Sprite("door", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(door);

        door_open = new Sprite("door_open", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(door_open);

        finish = new Sprite("finish", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(finish);

        destroyed = new Sprite("destroyed", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(destroyed);

        fire = new Sprite[4];
        for (int i = 0; i < fire.length; i++) {
            fire[i] = new Sprite("fire_" + i, Integers.RASTER_SIZE, i * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
            registerSprite(fire[i]);
        }

        water = new Sprite("water", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(water);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param s
     *            Sprite
     */
    private static void registerSprite(Sprite s) {
        if (s != null && !MAPPINGS.containsKey(s.getName())) {
            MAPPINGS.put(s.getName(), s);
        } else {
            Logger.warn("Unable to register Sprite", s);
            throw new IllegalArgumentException("Unable to register Sprite " + s);
        }
    }

    private Sprites() {
    }

}
