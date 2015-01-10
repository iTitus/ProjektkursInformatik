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
     * Zerstörter Boden.
     */
    public static AbstractRaster destroyedRaster;
    /**
     * Tür.
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
    public static SolidRaster water;

    /**
     * Das Pair, das alle Kommandos enthält.
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
        wall = new SolidRaster((byte) 1, "wall", Images.wand);
        registerRaster(wall);

        chest = new ChestRaster((byte) 2);
        registerRaster(chest);

        rasen = new SimpleRaster((byte) 3, "grass", Images.rasen3);
        registerRaster(rasen);

        baum = new SolidRaster((byte) 4, "tree", Images.baum);
        registerRaster(baum);

        door = new DoorRaster((byte) 5);
        registerRaster(door);

        finish = new FinishRaster((byte) 6);
        registerRaster(finish);

        destroyedRaster = new SimpleRaster((byte) 7, "destroyedRaster", Images.destroyedRaster);
        registerRaster(destroyedRaster);

        fire = new FireRaster((byte) 8);
        registerRaster(fire);

        water = new SolidRaster((byte) 9, "water", Images.wasser);
        registerRaster(water);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param r
     *            Raster
     */
    private static void registerRaster(AbstractRaster r) {
        if (r != null && !MAPPINGS.containsKey(r.getName()) && RASTER[r.getID()] != null) {
            MAPPINGS.put(r.getName(), r);
            RASTER[r.getID()] = r;
        } else {
            Logger.warn("Unable to register Raster", r);
        }
    }

    /**
     * Konstruktor.
     */
    private Raster() {
    }

}
