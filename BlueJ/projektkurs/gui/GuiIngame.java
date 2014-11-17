package projektkurs.gui;

import projektkurs.Main;
import projektkurs.lib.KeyBindings;

public class GuiIngame extends Gui {

	@Override
	public void onKeyTyped(char keyChar, int modifiers) {
		if (keyChar == KeyBindings.KEY_OPTION) {
			Main.openGui(new GuiOption());
		}
	}

	@Override
	public void onLeftClick(int screenX, int screenY) {
		super.onLeftClick(screenX, screenY);
		Main.getPlayer().onLeftClick(screenX, screenY);
	}

	@Override
	public void onRightClick(int screenX, int screenY) {
		super.onRightClick(screenX, screenY);
		Main.getPlayer().onRightClick(screenX, screenY);
	}
}
