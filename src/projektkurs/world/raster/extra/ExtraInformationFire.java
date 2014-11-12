package projektkurs.world.raster.extra;

import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.Raster;

public class ExtraInformationFire extends ExtraInformation {

	private AbstractRaster background;

	public ExtraInformationFire(int x, int y) {
		super(x, y);
		background = Raster.destroyedRaster;
	}

	public AbstractRaster getBackground() {
		return background;
	}

	public void setBackground(AbstractRaster background) {
		this.background = background;
	}

}
