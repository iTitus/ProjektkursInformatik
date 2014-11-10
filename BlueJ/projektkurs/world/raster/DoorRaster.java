package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.IHasExtraInformation;

public class DoorRaster extends AbstractRaster implements IHasExtraInformation {

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		((ExtraInformationDoor) Main.getSpielfeld().getExtraInformationAt(x, y))
				.tryOpen(Main.getPlayer().getInventory().getSelectedItemStack());
		return ((ExtraInformationDoor) Main.getSpielfeld()
				.getExtraInformationAt(x, y)).getIsOpen(dir);
	}

	@Override
	public ExtraInformation getExtraInformation(int x, int y) {
		return new ExtraInformationDoor();
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		RenderUtil.drawDefaultRaster(g, ((ExtraInformationDoor) Main
				.getSpielfeld().getExtraInformationAt(x, y)).getImage(), x, y);
	}

	@Override
	public void renderCutScene(Graphics2D g, int x, int y) {
		RenderUtil.drawCutSceneRaster(g, ((ExtraInformationDoor) Main
				.getSpielfeld().getExtraInformationAt(x, y)).getImage(), x, y);
	}
}
