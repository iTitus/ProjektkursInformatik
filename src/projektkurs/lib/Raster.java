package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.raster.AbstractRaster;
import projektkurs.raster.ChestRaster;
import projektkurs.raster.DoorRaster;
import projektkurs.raster.FinishRaster;
import projektkurs.raster.FireRaster;
import projektkurs.raster.SimpleRaster;
import projektkurs.raster.SolidRaster;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Raster.
 */
public final class Raster {

    /**
     * Baum.
     */
    public static AbstractRaster baum;
    /**
     * Kiste.
     */
    public static AbstractRaster chest;
    /**
     * Zerstoerter Boden.
     */
    public static AbstractRaster destroyedRaster;
    /**
     * Tuer.
     */
    public static AbstractRaster door;
    /**
     * Ziel.
     */
    public static AbstractRaster finish;
    /**
     * Feuer.
     */
    public static AbstractRaster fire;
    /**
     * Mappings.
     */
    public static final HashMap<String, AbstractRaster> MAPPINGS = new HashMap<String, AbstractRaster>();
    /**
     * Rasen.
     */
    public static AbstractRaster rasen;
    /**
     * Array aller Raster nach ihrer ID.
     */
    public static final AbstractRaster[] RASTER = new AbstractRaster[Integers.MAX_RASTER_NUMBER];
    /**
     * Wand.
     */
    public static AbstractRaster wall;
    /**
     * Wasser
     */
    public static AbstractRaster water;

    /**
     * Das Pair, das alle Raster enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.raster", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Items.
     */
    @Init(state = State.PRE)
    public static void init() {
        wall = new SolidRaster(1, "wall", Sprites.wall);
        registerRaster(wall);

        rasen = new SimpleRaster(2, "grass", Sprites.grass);
        registerRaster(rasen);

        baum = new SolidRaster(3, "tree", Sprites.tree);
        registerRaster(baum);

        chest = new ChestRaster(4);
        registerRaster(chest);

        door = new DoorRaster(5);
        registerRaster(door);

        finish = new FinishRaster(6);
        registerRaster(finish);

        destroyedRaster = new SimpleRaster(7, "destroyedRaster", Sprites.destroyed);
        registerRaster(destroyedRaster);

        fire = new FireRaster(8);
        registerRaster(fire);

        water = new SolidRaster(9, "water", Sprites.water);
        registerRaster(water);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param r
     *            Raster
     */
    private static void registerRaster(AbstractRaster r) {
        if (r != null && !MAPPINGS.containsKey(r.getName()) && r.getID() > 0 && r.getID() < RASTER.length && RASTER[r.getID()] == null) {
            MAPPINGS.put(r.getName(), r);
            RASTER[r.getID()] = r;
        } else {
            Logger.warn("Unable to register Raster", r);
            throw new IllegalArgumentException("Unable to register Raster " + r);
        }
    }

    /**
     * Konstruktor.
     */
    private Raster() {
    }
}
