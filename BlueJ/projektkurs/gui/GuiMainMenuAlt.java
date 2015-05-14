package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Integers;

public class GuiMainMenuAlt extends Gui implements IButtonListener {

	@Override
	public void initGui() {
		super.initGui();
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 0, this, "button.startGame"));
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y + Integers.DEFAULT_BUTTON_HEIGHT, 1, this, "button.options"));
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y + 2 * Integers.DEFAULT_BUTTON_HEIGHT, 2, this, "button.exit"));
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT, 42, this, "Normal Main Menu"));
	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		switch (button.getID()) {
			case 0:
				Main.openGui(new GuiStartGame(this));
				break;
			case 1:
				Main.openGui(new GuiOption(this));
				break;
			case 2:
				Main.exit();
				break;
			case 42:
				Main.openGui(new GuiMainMenu());
				break;
			default:
				break;
		}
	}

	@Override
	public void onButtonRightClick(Button button, MouseEvent e) {
		// NO-OP
	}

}
