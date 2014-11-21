package projektkurs.lib;

import java.util.HashMap;

import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.DoorRaster;
import projektkurs.world.raster.FinishRaster;
import projektkurs.world.raster.FireRaster;
import projektkurs.world.raster.KistenRaster;
import projektkurs.world.raster.SimpleRaster;
import projektkurs.world.raster.SolidRaster;

public final class Raster {

    public static final HashMap<AbstractRaster, String> BACK_MAPPINGS = new HashMap<AbstractRaster, String>();
    public static AbstractRaster                        baum, door, finish, kiste, rasen, wand, destroyedRaster, fire;
    public static final HashMap<String, AbstractRaster> MAPPINGS      = new HashMap<String, AbstractRaster>();

    @Init(state = State.PRE)
    public static void init() {
        wand = new SolidRaster(Images.wand);
        registerRaster("wand", wand);

        kiste = new KistenRaster();
        registerRaster("kiste", kiste);

        rasen = new SimpleRaster(Images.rasen);
        registerRaster("rasen", rasen);

        baum = new SolidRaster(Images.baum);
        registerRaster("baum", baum);

        door = new DoorRaster();
        registerRaster("door", door);

        finish = new FinishRaster();
        registerRaster("finish", finish);

        destroyedRaster = new SimpleRaster(Images.destroyedRaster);
        registerRaster("destroyedRaster", destroyedRaster);

        fire = new FireRaster();
        registerRaster("fire", fire);

    }

    private static void registerRaster(String name, AbstractRaster r) {
        MAPPINGS.put(name, r);
        BACK_MAPPINGS.put(r, name);
    }

    private Raster() {
    }

}
