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

    public static Sprite button;
    public static Sprite button_disabled;
    public static Sprite button_highlight;
    public static Sprite chest;
    public static Sprite destroyed;
    public static Sprite door;
    public static Sprite door_open;
    public static Sprite finish;
    public static Sprite[] fire;
    public static Sprite grass;
    public static Sprite healthpotion;
    public static Sprite item42;
    public static Sprite key;
    public static Sprite lordvO_N;
    public static Sprite lordvO_O;
    public static Sprite lordvO_S;
    public static Sprite lordvO_W;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Sprite> MAPPINGS = new HashMap<String, Sprite>();
    public static Sprite nuke;
    public static Sprite redNPC;
    public static Sprite slot;
    public static Sprite slot_highlight;
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

        item42 = new Sprite("item42", Integers.RASTER_SIZE, 0, 0, SpriteSheets.items);
        registerSprite(item42);

        nuke = new Sprite("nuke", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(nuke);

        key = new Sprite("key", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(key);

        healthpotion = new Sprite("healthpotion", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(healthpotion);

        button = new Sprite("button", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, 0, SpriteSheets.guis);
        registerSprite(button);

        button_highlight = new Sprite("button_highlight", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(button_highlight);

        button_disabled = new Sprite("button_disabled", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, 2 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(button_disabled);

        slot = new Sprite("slot", Integers.SLOT_SIZE, 0, 3 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(slot);

        slot_highlight = new Sprite("slot_highlight", Integers.SLOT_SIZE, Integers.SLOT_SIZE, 3 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(slot_highlight);

        lordvO_W = new Sprite("lordvO_W", Integers.RASTER_SIZE, 0, 0, SpriteSheets.entities);
        registerSprite(lordvO_W);

        lordvO_N = lordvO_W.rotate("lordvO_N", Math.toRadians(90));
        registerSprite(lordvO_N);

        lordvO_O = lordvO_N.rotate("lordvO_O", Math.toRadians(90));
        registerSprite(lordvO_O);

        lordvO_S = lordvO_O.rotate("lordvO_S", Math.toRadians(90));
        registerSprite(lordvO_S);

        redNPC = new Sprite("redNPC", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(redNPC);
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
