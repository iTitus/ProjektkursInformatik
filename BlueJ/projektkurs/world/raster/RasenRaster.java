package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Images;

/**
 * Ein Stück Rasen
 *
 */
public class RasenRaster extends AbstractRaster {

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.rasen;
	}

}