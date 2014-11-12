package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.render.AnimationFrame;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationFire;
import projektkurs.world.raster.extra.IHasExtraInformation;

public class FireRaster extends AnimatedRaster implements IHasExtraInformation {

	public FireRaster() {
		super(AnimationFrame.getSynchronousAnimation(Images.fire, 5));
	}

	@Override
	public ExtraInformation getExtraInformation(int x, int y) {
		return new ExtraInformationFire(x, y);
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		((ExtraInformationFire) Main.getLevel().getCurrMap()
				.getExtraInformationAt(x, y)).getBackground().render(g, x, y);
		super.render(g, x, y);
	}

}
