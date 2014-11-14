package projektkurs.gui;

import projektkurs.Main;
import projektkurs.lib.KeyBindings;

public class GuiIngame extends Gui {

	@Override
	public void onKeyPressed(int key) {
		if (key == KeyBindings.KEY_EXIT) {
			Main.exit();
		}
	}

	@Override
	public void onLeftClick(int screenX, int screenY) {
		Main.getPlayer().onLeftClick(screenX, screenY);
	}

	@Override
	public void onRightClick(int screenX, int screenY) {
		Main.getPlayer().onRightClick(screenX, screenY);
	}
}
