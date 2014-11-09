package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.util.Direction;

/**
 * Solides Raster
 */
public class SolidRaster extends SimpleRaster {

	public SolidRaster(BufferedImage image) {
		super(image);
	}

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return false;
	}

}
