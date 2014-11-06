package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.lib.Images;
import projektkurs.story.scripts.Scripts;
import projektkurs.util.Direction;

public class FinishRaster extends AbstractRaster {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		Scripts.win();
		return super.canWalkOnFromDirection(x, y, dir);
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.finish;
	}

}
