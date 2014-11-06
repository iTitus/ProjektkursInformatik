package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.util.Direction;
import projektkurs.util.Logger;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;

/**
 * Ein Raster mit einer Kiste
 */
public class KistenRaster extends AbstractRaster {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {

		ExtraInformationKiste kiste = (ExtraInformationKiste) Main
				.getSpielfeld().getExtraInformationAt(x, y);

		Logger.info("[Kiste @{x=" + x + ", y=" + y
				+ "}] Aua, ich habe dies für dich: "
				+ kiste.getInventar().toString());
		return false;
	}

	@Override
	public ExtraInformation getExtraInformation() {
		return new ExtraInformationKiste();
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.kiste;
	}

	@Override
	public boolean isInteractive() {
		return true;
	}

}
