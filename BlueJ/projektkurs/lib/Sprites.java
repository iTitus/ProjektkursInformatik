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
    public static Sprite door_NS;
    public static Sprite door_open_NS;
    public static Sprite door_open_WE;
    public static Sprite door_WE;
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
    public static final Sprite MISSING_ICON = new Sprite("MISSING_ICON", 16, 0, 0xFF01FF, 0, 0xFF01FF);
    public static Sprite nuke;
    public static Sprite rasen_2;
    public static Sprite redNPC;
    public static Sprite slot;
    public static Sprite slot_highlight;
    public static Sprite tree;
    public static Sprite tree_2ne;
    public static Sprite tree_2nw;
    public static Sprite tree_2se;
    public static Sprite tree_2sw;
    public static Sprite tree_3ne;
    public static Sprite tree_3nw;
    public static Sprite tree_3se;
    public static Sprite tree_3sw;
    public static Sprite tree_4ne;
    public static Sprite tree_4nw;
    public static Sprite tree_4se;
    public static Sprite tree_4sw;
    public static Sprite wall;
    public static Sprite water;

    /**
     * Das Pair, das alle Sprites enthaelt.
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

        door_NS = new Sprite("door_NS", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(door_NS);

        door_WE = door_NS.rotate("door_WE", 90);
        registerSprite(door_WE);

        door_open_NS = new Sprite("door_open_NS", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(door_open_NS);

        door_open_WE = door_open_NS.rotate("door_open_WE", 90);
        registerSprite(door_open_WE);

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

        rasen_2 = new Sprite("rasen_2", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(rasen_2);

        tree_2nw = new Sprite("tree_2nw", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2nw);

        tree_2ne = new Sprite("tree_2ne", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2ne);

        tree_2se = new Sprite("tree_2se", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2se);

        tree_2sw = new Sprite("tree_2sw", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2sw);

        tree_3nw = new Sprite("tree_3nw", Integers.RASTER_SIZE, 0, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3nw);

        tree_3ne = new Sprite("tree_3ne", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3ne);

        tree_3se = new Sprite("tree_3se", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3se);

        tree_3sw = new Sprite("tree_3sw", Integers.RASTER_SIZE, 0, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3sw);

        tree_4nw = new Sprite("tree_4nw", Integers.RASTER_SIZE, 0, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4nw);

        tree_4ne = new Sprite("tree_4ne", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4ne);

        tree_4se = new Sprite("tree_4se", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4se);

        tree_4sw = new Sprite("tree_4sw", Integers.RASTER_SIZE, 0, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4sw);

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

        lordvO_N = new Sprite("lordvO_N", Integers.RASTER_SIZE, 0, 0, SpriteSheets.entities);
        registerSprite(lordvO_N);

        lordvO_O = lordvO_N.rotate("lordvO_O", 90);
        registerSprite(lordvO_O);

        lordvO_S = lordvO_N.rotate("lordvO_S", 180);
        registerSprite(lordvO_S);

        lordvO_W = lordvO_N.rotate("lordvO_W", 270);
        registerSprite(lordvO_W);

        redNPC = new Sprite("redNPC", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(redNPC);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param s
     *            Sprite
     */
    public static void registerSprite(Sprite s) {
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
