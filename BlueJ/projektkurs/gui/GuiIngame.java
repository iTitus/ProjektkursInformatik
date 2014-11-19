package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.story.script.Scripts;
import projektkurs.util.MathUtil;
import projektkurs.world.raster.AbstractRaster;

public class GuiIngame extends Gui {

	@Override
	public void onKeyTyped(char keyChar, KeyEvent e) {
		if (keyChar == KeyBindings.KEY_OPTION) {
			Main.openGui(new GuiOption());
		}
	}

	@Override
	public void onLeftClick(int screenX, int screenY, MouseEvent e) {
		super.onLeftClick(screenX, screenY, e);

		int rX = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_X,
				Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
		int rY = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_Y,
				Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

		AbstractRaster r = Main.getLevel().getCurrMap().getRasterAt(rX, rY);
		if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
			r.onLeftClick(rX, rY, e);
		}

		Main.getPlayer().onLeftClick(screenX, screenY, e);
	}

	@Override
	public void onMouseWheelMoved(int by, MouseWheelEvent e) {
		if (by > 0) {
			Main.getPlayer()
					.getInventory()
					.setSelectedItemStack(
							Main.getPlayer().getInventory().getSelectedIndex() >= Main
									.getPlayer().getInventory().getSize() ? 0
									: Main.getPlayer().getInventory()
											.getSelectedIndex() + 1);
		} else if (by < 0) {
			Main.getPlayer()
					.getInventory()
					.setSelectedItemStack(
							(Main.getPlayer().getInventory().getSelectedIndex() <= 0 ? Main
									.getPlayer().getInventory().getSize() - 1
									: Main.getPlayer().getInventory()
											.getSelectedIndex() - 1));
		}
	}

	@Override
	public void onRightClick(int screenX, int screenY, MouseEvent e) {
		super.onRightClick(screenX, screenY, e);

		int rX = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_X,
				Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
		int rY = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_Y,
				Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

		AbstractRaster r = Main.getLevel().getCurrMap().getRasterAt(rX, rY);
		if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
			r.onRightClick(rX, rY, e);
		}

		Main.getPlayer().onRightClick(screenX, screenY, e);

		if (e.isShiftDown())
			Scripts.cutSceneOne();
	}
}
