package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Images;
import projektkurs.util.Direction;

/**
 * Ein Baum
 * 
 */
public class BaumRaster extends AbstractRaster {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return false;
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.baum;
	}

}
