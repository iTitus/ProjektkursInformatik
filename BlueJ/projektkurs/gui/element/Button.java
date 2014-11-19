package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.util.I18n;
import projektkurs.util.RenderUtil;

public class Button extends Element {

	protected boolean enabled;
	protected IButtonListener gui;
	protected String name;

	public Button(int posX, int posY, int sizeX, int sizeY, int id,
			IButtonListener gui, String name) {
		super(posX, posY, sizeX, sizeY, id);
		this.name = name;
		this.gui = gui;
		enabled = true;
	}

	public String getName() {
		return name;
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			gui.onButtonLeftClick(this);
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			gui.onButtonRightClick(this);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (enabled) {
			if (isInside(Main.getInputManager().getMouseX(), Main
					.getInputManager().getMouseY()))
				RenderUtil.drawImage(g, Images.button_highlight, posX, posY,
						sizeX, sizeY);
			else
				RenderUtil
						.drawImage(g, Images.button, posX, posY, sizeX, sizeY);
		} else
			RenderUtil.drawImage(g, Images.button_disabled, posX, posY, sizeX,
					sizeY);

		RenderUtil.drawCenteredStringInRect(g, I18n.getString(name), posX,
				posY, sizeX, sizeY);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
