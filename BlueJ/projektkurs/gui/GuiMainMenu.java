package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Configs;
import projektkurs.lib.Integers;
import projektkurs.lib.Levels;

/**
 * Das Hauptmenue.
 */
public class GuiMainMenu extends Gui implements IButtonListener {

	@Override
	public void initGui() {
		super.initGui();
		if (Configs.debugMode.getValue()) {
			addElement(new Button(64, 64, 0, this, "Level0"));
		}
		addElement(new Button(64, 128, 1, this, "button.startLevel1"));
		addElement(new Button(64, 192, 2, this, "button.options"));
		addElement(new Button(64, 256, 3, this, "button.exit"));
		if (Configs.debugMode.getValue()) {
			addElement(new Button(Integers.WINDOW_HUD_X, Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT, 42, this, "Alternative Main Menu"));
		}
	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		switch (button.getID()) {
			case 0:
				Main.startLevel(Levels.level0);
				break;
			case 1:
				Main.startLevel(Levels.level1);
				break;
			case 2:
				Main.openGui(new GuiOption(this));
				break;
			case 3:
				Main.exit();
				break;
			case 42:
				Main.openGui(new GuiMainMenuAlt());
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
