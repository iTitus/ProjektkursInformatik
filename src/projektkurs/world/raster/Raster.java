package projektkurs.world.raster;

import java.util.HashMap;

import projektkurs.lib.Images;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

public final class Raster {

	public static AbstractRaster baum, door, finish, kiste, rasen, wand,
			destroyedRaster, fire;
	public static final HashMap<String, AbstractRaster> MAPPINGS = new HashMap<String, AbstractRaster>();

	@Init(state = State.PRE)
	public static void init() {
		wand = new SolidRaster(Images.wand);
		MAPPINGS.put("wand", wand);

		kiste = new KistenRaster();
		MAPPINGS.put("kiste", kiste);

		rasen = new SimpleRaster(Images.rasen);
		MAPPINGS.put("rasen", rasen);

		baum = new SolidRaster(Images.baum);
		MAPPINGS.put("baum", baum);

		door = new DoorRaster();
		MAPPINGS.put("door", door);

		finish = new FinishRaster();
		MAPPINGS.put("finish", finish);

		destroyedRaster = new SimpleRaster(Images.destroyedRaster);
		MAPPINGS.put("destroyedRaster", destroyedRaster);

		fire = new FireRaster();
		MAPPINGS.put("fire", fire);

	}

	private Raster() {
	}

}