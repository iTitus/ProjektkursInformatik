package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Direction;
import projektkurs.lib.Images;

/**
 * Eine Wand
 *
 */
public class WandRaster extends AbstractRaster {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return false;
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.wand;
	}

}
