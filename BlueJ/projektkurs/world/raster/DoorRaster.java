package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.util.Direction;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;

public class DoorRaster extends AbstractRaster {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		((ExtraInformationDoor) Main.getSpielfeld().getExtraInformationAt(x, y))
				.tryOpen(Main.getFigur().getInventory().getSelectedItemStack());
		return ((ExtraInformationDoor) Main.getSpielfeld()
				.getExtraInformationAt(x, y)).getIsOpen(dir);
	}

	@Override
	public ExtraInformation getExtraInformation() {
		return new ExtraInformationDoor();
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return ((ExtraInformationDoor) Main.getSpielfeld()
				.getExtraInformationAt(x, y)).getBufferedImage();
	}
}
