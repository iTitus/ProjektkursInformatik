package projektkurs.gui.element;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.util.I18n;
import projektkurs.util.RenderUtil;

public class Button extends Element {

	protected boolean hovered, enabled;
	protected String name;

	public Button(int posX, int posY, int sizeX, int sizeY, int id, String name) {
		super(posX, posY, sizeX, sizeY, id);
		this.name = name;
		hovered = false;
		enabled = true;
	}

	public String getName() {
		return name;
	}

	public boolean isHovered() {
		return hovered;
	}

	@Override
	public void onLeftClick(int x, int y) {
		if (isInside(x, y)) {
			Main.getGui().onButtonLeftClick(this);
		}
	}

	@Override
	public void onRightClick(int x, int y) {
		if (isInside(x, y)) {
			Main.getGui().onButtonRightClick(this);
		}
	}

	@Override
	public void render(Graphics2D g) {
		hovered = isInside(Main.getInputManager().getMouseX(), Main
				.getInputManager().getMouseY());
		if (enabled) {
			if (hovered)
				RenderUtil.drawImage(g, Images.button_highlight, posX, posY,
						sizeX, sizeY);
			else
				RenderUtil
						.drawImage(g, Images.button, posX, posY, sizeX, sizeY);
		} else
			RenderUtil.drawImage(g, Images.button_disabled, posX, posY, sizeX,
					sizeY);

		RenderUtil.drawCenteredButtonString(g, I18n.getString(name), posX,
				posY, sizeX, sizeY);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
