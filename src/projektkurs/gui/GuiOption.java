package projektkurs.gui;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.ITextFieldListener;
import projektkurs.gui.element.TextField;
import projektkurs.gui.element.ToggleButton;
import projektkurs.lib.Integers;
import projektkurs.lib.Sounds;
import projektkurs.util.MathUtil;

public class GuiOption extends Gui implements IButtonListener,
		ITextFieldListener {

	@Override
	public void initGui() {
		guiElements.add(new Button(MathUtil.ceilDiv(Integers.WINDOW_X, 2)
				- MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2),
				Integers.WINDOW_Y - Integers.WINDOW_HUD_Y - 64,
				Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT,
				0, this, "button.exit"));
		ToggleButton soundButton = new ToggleButton(MathUtil.ceilDiv(
				Integers.WINDOW_X, 2)
				- MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2),
				Integers.WINDOW_Y - Integers.WINDOW_HUD_Y
						- (Integers.DEFAULT_BUTTON_HEIGHT * 2),
				Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT,
				1, this, "button.sound.on", "button.sound.off");
		soundButton.setIndex(Sounds.isMuted() ? 1 : 0);
		guiElements.add(soundButton);

		guiElements.add(new TextField(32, 32, 256, 64, 2, this, "Test1234"));
	}

	@Override
	public void onButtonLeftClick(Button button) {
		switch (button.getID()) {
		case 0:
			Main.exit();
			break;
		case 1:
			Sounds.mute(((ToggleButton) button).getIndex() != 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void onButtonRightClick(Button button) {
		switch (button.getID()) {
		case 1:
			Sounds.mute(((ToggleButton) button).getIndex() != 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);
		super.render(g);
	}

}
