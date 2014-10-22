package projektkurs.world.raster;

import java.util.HashMap;

import projektkurs.lib.Init;
import projektkurs.lib.Init.State;

public final class Raster {

	public static BaumRaster BAUM = new BaumRaster();

	public static KistenRaster KISTE = new KistenRaster();
	public static final HashMap<String, AbstractRaster> MAPPINGS = new HashMap<String, AbstractRaster>();
	public static RasenRaster RASEN = new RasenRaster();
	public static WandRaster WAND = new WandRaster();

	@Init(state = State.PRE)
	public static void init() {
		WAND = new WandRaster();
		MAPPINGS.put("wand", WAND);

		KISTE = new KistenRaster();
		MAPPINGS.put("kiste", KISTE);

		RASEN = new RasenRaster();
		MAPPINGS.put("rasen", RASEN);

		BAUM = new BaumRaster();
		MAPPINGS.put("baum", BAUM);
	}

	private Raster() {
	}

}