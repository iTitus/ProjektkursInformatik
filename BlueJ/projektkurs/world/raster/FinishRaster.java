package projektkurs.world.raster;

import projektkurs.entity.Entity;
import projektkurs.lib.Images;
import projektkurs.story.scripts.Scripts;
import projektkurs.util.Direction;

public class FinishRaster extends SimpleRaster {

	public FinishRaster() {
		super(Images.finish);
	}

	@Override
	public void onWalkOnFromDirection(int x, int y, Entity entity, Direction d) {
		Scripts.win();
		super.onWalkOnFromDirection(x, y, entity, d);
	}

}
