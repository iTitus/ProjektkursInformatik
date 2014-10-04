package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Direction;
import projektkurs.lib.Images;

/**
 * Eine Wand
 *
 */
public class WandRaster extends Raster {

	private static final WandRaster INSTANCE = new WandRaster();

	/**
	 * Die Instanz
	 * 
	 * @return
	 */
	public static WandRaster getInstance() {
		return INSTANCE;
	}

	private WandRaster() {
	}

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return false;
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.wand;
	}

}
