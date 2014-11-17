package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.event.InputEvent;

import projektkurs.lib.KeyBindings;

public class TextField extends Element {

	protected boolean focussed;
	protected ITextFieldListener gui;
	protected String text;

	public TextField(int posX, int posY, int sizeX, int sizeY, int id,
			ITextFieldListener gui) {
		this(posX, posY, sizeX, sizeY, id, gui, "");
	}

	public TextField(int posX, int posY, int sizeX, int sizeY, int id,
			ITextFieldListener gui, String text) {
		super(posX, posY, sizeX, sizeY, id);
		this.gui = gui;
		this.text = text;
	}

	@Override
	public void onKeyTyped(char keyChar, int modifiers) {
		if (focussed) {
			if (keyChar == KeyBindings.BACK_SPACE) {
				if (text.length() > 0)
					text = text.substring(0, text.length() - 1);
			} else if ((modifiers & (InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK)) != 0) {
				text += keyChar;
			}
		}
	}

	@Override
	public void onLeftClick(int x, int y) {
		if (isInside(x, y)) {
			focussed = true;
		} else {
			focussed = false;
		}
	}

	@Override
	public void onRightClick(int x, int y) {
		if (isInside(x, y) && focussed) {
			text = "";
		} else {
			focussed = false;
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawString(text + (focussed ? "|" : ""), posX, posY);
	}

}
