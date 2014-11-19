package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class TextField extends Element {

	protected boolean focussed, enabled;
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
		focussed = false;
		enabled = true;
	}

	public String getText() {
		return text;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isFocussed() {
		return focussed;
	}

	@Override
	public void onKeyTyped(char keyChar, KeyEvent e) {
		if (enabled && focussed) {
			if (keyChar == KeyBindings.BACK_SPACE) {
				if (text.length() > 0)
					text = text.substring(0, text.length() - 1);
				gui.onTextChanged(this);
			} else if (keyChar == KeyBindings.LINE_BREAK) {
				focussed = false;
				gui.onFocusLost(this);
			} else if (e.isControlDown()) {
				if (keyChar == KeyBindings.PASTE_KEY) {
					try {
						text += Toolkit.getDefaultToolkit()
								.getSystemClipboard().getContents(null)
								.getTransferData(DataFlavor.stringFlavor);
						gui.onTextChanged(this);
					} catch (Throwable t) {
						Logger.logThrowable(
								"Unable to paste clipboard contents", t);
					}
				}
			} else if (!e.isAltDown() && !e.isAltGraphDown() && !e.isMetaDown()) {
				text += keyChar;
				gui.onTextChanged(this);
			}
		}
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		if (enabled) {
			if (isInside(x, y)) {
				focussed = true;
				gui.onFocusGained(this);
			} else {
				focussed = false;
				gui.onFocusLost(this);
			}
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		if (enabled) {
			if (isInside(x, y)) {
				if (focussed) {
					text = "";
					gui.onTextChanged(this);
				} else {
					focussed = true;
					gui.onFocusGained(this);
				}
			} else {
				focussed = false;
				gui.onFocusLost(this);
			}
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

	public void setEnabled(boolean enabled) {
		if (this.enabled != enabled) {
			this.enabled = enabled;
			if (!this.enabled) {
				focussed = false;
				gui.onFocusLost(this);
			}
		}
	}

	public void setFocused(boolean focussed) {
		if (enabled && this.focussed != focussed) {
			this.focussed = focussed;
			if (this.focussed)
				gui.onFocusGained(this);
			else
				gui.onFocusLost(this);
		}
	}

	public void setText(String text) {
		this.text = text;
	}

}
