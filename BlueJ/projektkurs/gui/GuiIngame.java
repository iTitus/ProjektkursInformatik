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
}
