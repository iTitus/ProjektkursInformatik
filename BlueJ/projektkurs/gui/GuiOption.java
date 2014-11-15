package projektkurs.gui;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;

public class GuiOption extends Gui {

	@Override
	public void initGui() {
		guiElements.add(new Button(MathUtil.ceilDiv(Integers.WINDOW_X, 2)
				- MathUtil.ceilDiv(256, 2), Integers.WINDOW_Y
				- Integers.WINDOW_HUD_Y - 64, 256, 64, 0, "button.exit"));
	}

	@Override
	public void onButtonClick(Button button) {
		switch (button.getID()) {
		case 0:
			Main.exit();
			break;
		default:
			Logger.debug(button.getID() + " was pressed!");
			break;
		}
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);
		super.render(g);
	}

}
