package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Images;

/**
 * Ein Stück Rasen
 *
 */
public class RasenRaster extends Raster {

	private static final RasenRaster INSTANCE = new RasenRaster();

	/**
	 * Die Instanz
	 * 
	 * @return
	 */
	public static RasenRaster getInstance() {
		return INSTANCE;
	}

	private RasenRaster() {
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.rasen;
	}

}