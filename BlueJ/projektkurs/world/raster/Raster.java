package projektkurs.world.raster;

import java.util.HashMap;

public final class Raster {

	public static final BaumRaster BAUM = new BaumRaster();

	public static final KistenRaster KISTE = new KistenRaster();
	public static final HashMap<String, AbstractRaster> MAPPINGS = new HashMap<String, AbstractRaster>();
	public static final RasenRaster RASEN = new RasenRaster();
	public static final WandRaster WAND = new WandRaster();

	static {
		MAPPINGS.put("wand", WAND);
		MAPPINGS.get("wand");
	}

	private Raster() {
	}

}