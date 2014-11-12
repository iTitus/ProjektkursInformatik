package projektkurs.world.raster;

import projektkurs.Main;
import projektkurs.gui.GuiInventory;
import projektkurs.lib.Images;
import projektkurs.util.Direction;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;
import projektkurs.world.raster.extra.IHasExtraInformation;

/**
 * Ein Raster mit einer Kiste
 */
public class KistenRaster extends SolidRaster implements IHasExtraInformation {

	public KistenRaster() {
		super(Images.kiste);
	}

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		Main.openGui(new GuiInventory(((ExtraInformationKiste) Main.getLevel()
				.getCurrMap().getExtraInformationAt(x, y)).getInventar()));
		return super.canWalkOnFromDirection(x, y, dir);
	}

	@Override
	public ExtraInformation getExtraInformation(int x, int y) {
		return new ExtraInformationKiste(x, y);
	}

}
