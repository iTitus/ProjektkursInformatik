package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.TextField;
import projektkurs.io.storage.Save;
import projektkurs.io.storage.SaveIO;
import projektkurs.io.storage.Saves;
import projektkurs.lib.Integers;

public class GuiCreateGame extends Gui implements IButtonListener {

	private TextField textField;

	public GuiCreateGame(GuiStartGame parent) {
		super(parent);
	}

	@Override
	public void initGui() {
		super.initGui();
		textField = new TextField(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.DEFAULT_BUTTON_HEIGHT, 0, null);
		addElement(textField);
		addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y + Integers.DEFAULT_BUTTON_HEIGHT, 1, this, "button.createNewSave"));
		addElement(new Button(Integers.WINDOW_HUD_X + Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y + Integers.DEFAULT_BUTTON_HEIGHT, 2, this, "button.cancel"));
	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		switch (button.getID()) {
			case 1:
				Saves saves = ((GuiStartGame) getParent()).getSaves();
				Save s = saves.createNewSave(textField.getText());
				if (s == null) {
					return;
				}
				SaveIO.writeSaves(saves);
				Main.openGui(new GuiStartGame(getParent().getParent()));
				break;
			case 2:
				Main.openGui(getParent());
				break;
			default:
				break;
		}
	}

	@Override
	public void onButtonRightClick(Button button, MouseEvent e) {
		// No-OP
	}

}
