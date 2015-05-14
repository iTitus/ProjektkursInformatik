package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.io.storage.Save;
import projektkurs.io.storage.SaveIO;
import projektkurs.io.storage.Saves;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

public class GuiStartGame extends Gui implements IButtonListener {

	private Saves saves;

	public GuiStartGame(Gui parent) {
		super(parent);
	}

	public Saves getSaves() {
		return saves;
	}

	@Override
	public void initGui() {
		super.initGui();
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 0, this, "button.newSave"));
		addElement(new Button(Integers.WINDOW_HUD_X + Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y, 1, this, "button.reload"));
		addElement(new Button(Integers.WINDOW_HUD_X + 2 * Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y, 2, this, "button.backToMainMenu"));
		int j = MathUtil.floorDiv(Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.DEFAULT_BUTTON_WIDTH);
		if (saves == null) {
			saves = SaveIO.loadSaves();
		}
		for (int i = 0; i < saves.getNumberOfSaves(); i++) {
			addElement(new Button(Integers.WINDOW_HUD_X + i % j * Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y + (i / j + 1) * Integers.DEFAULT_BUTTON_HEIGHT, i + 3, this, saves.getSave(i).getDisplayName(), false));
		}
	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		if (button.getID() > 2) {
			Save s = saves.getSave(button.getID() - 3);
			if (s != null) {
				s.start();
			}
		} else {
			switch (button.getID()) {
				case 0:
					Main.openGui(new GuiCreateGame(this));
					break;
				case 1:
					Main.openGui(new GuiStartGame(getParent()));
					break;
				case 2:
					Main.openGui(new GuiMainMenuAlt());
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onButtonRightClick(Button button, MouseEvent e) {
		// NO-OP
	}

}
