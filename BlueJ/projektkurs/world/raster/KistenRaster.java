package projektkurs.world.raster;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.util.Direction;
import projektkurs.util.Logger;
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
		ExtraInformationKiste kiste = (ExtraInformationKiste) Main
				.getSpielfeld().getExtraInformationAt(x, y);
		Logger.info("[Kiste @{x=" + x + ", y=" + y + "}] "
				+ kiste.getInventar().toString());
		return super.canWalkOnFromDirection(x, y, dir);
	}

	@Override
	public ExtraInformation getExtraInformation(int x, int y) {
		return new ExtraInformationKiste();
	}

}
