package projektkurs.world.raster.extra;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.Raster;

public class ExtraInformationFire extends ExtraInformation {

	private AbstractRaster background;

	public ExtraInformationFire(int x, int y) {
		super(x, y);
		background = Raster.destroyedRaster;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public AbstractRaster getBackground() {
		return background;
	}

	public void setBackground(AbstractRaster background) {
		this.background = background;
	}

	@Override
	public void update() {
		Entity e = Main.getLevel().getCurrMap().getEntityAt(x, y);
		if (e != null && e instanceof EntityLiving)
			((EntityLiving) e).damage(1);
	}
}
