package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.Element;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;

/**
 * Ein Graphical User Interface
 *
 */
public abstract class Gui {

	private static final Color backgroundColor = new Color(127, 127, 127, 156);

	protected ArrayList<Element> guiElements;

	public Gui() {
		guiElements = new ArrayList<Element>();
	}

	public void initGui() {
		// NO-OP
	}

	public void onButtonLeftClick(Button button) {
		// NO-OP
	}

	public void onButtonRightClick(Button button) {
		// NO-OP
	}

	public void onKeyPressed(int key) {
		for (Element e : guiElements) {
			e.onKeyPressed(key);
		}
		if (key == KeyBindings.KEY_OPTION)
			Main.closeGui();
	}

	public void onLeftClick(int screenX, int screenY) {
		for (Element e : guiElements) {
			e.onLeftClick(screenX, screenY);
		}
	}

	public void onRightClick(int screenX, int screenY) {
		for (Element e : guiElements) {
			e.onRightClick(screenX, screenY);
		}
	}

	public void render(Graphics2D g) {
		for (Element e : guiElements) {
			e.render(g);
		}
	}

	protected void drawDefaultBackground(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setColor(backgroundColor);
		g.fillRect(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y,
				Integers.SIGHT_X * Integers.RASTER_SIZE, Integers.SIGHT_Y
						* Integers.RASTER_SIZE);
		g.setColor(oldColor);
	}
}
