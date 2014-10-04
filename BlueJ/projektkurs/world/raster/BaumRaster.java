package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Direction;
import projektkurs.lib.Images;

/**
 * Ein Baum
 * 
 */
public class BaumRaster extends Raster {

	private static final BaumRaster INSTANCE = new BaumRaster();

	/**
	 * Die Instanz
	 * 
	 * @return
	 */
	public static BaumRaster getInstance() {
		return INSTANCE;
	}

	private BaumRaster() {
	}

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return false;
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.baum;
	}

}
