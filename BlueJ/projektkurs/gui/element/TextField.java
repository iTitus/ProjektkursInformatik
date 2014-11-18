package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

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
			} else if (keyChar == KeyBindings.LINE_BREAK) {
				focussed = false;
			} else if ((modifiers & (InputEvent.CTRL_MASK | InputEvent.ALT_MASK)) != 0) {
				if (keyChar == KeyBindings.PASTE_KEY)
					try {
						text += Toolkit.getDefaultToolkit()
								.getSystemClipboard().getContents(null)
								.getTransferData(DataFlavor.stringFlavor);
					} catch (Throwable t) {
						Logger.logThrowable(
								"Unable to paste clipboard contents", t);
					}
			} else {
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
		g.drawRect(posX, posY, sizeX, sizeY);
		RenderUtil.drawCenteredStringInRect(
				g,
				text
						+ (focussed
								&& Main.getRenderHelper().getRenderTicks()
										% Integers.CURSOR_BLINK_TIME > MathUtil
											.floorDiv(
													Integers.CURSOR_BLINK_TIME,
													2) ? "|" : ""), posX, posY,
				sizeX, sizeY);
	}

}
