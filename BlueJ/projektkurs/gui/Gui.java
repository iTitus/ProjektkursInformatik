package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import projektkurs.Main;
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

	public void onKeyTyped(char keyChar, KeyEvent e) {
		for (Element el : guiElements) {
			el.onKeyTyped(keyChar, e);
		}
		if (keyChar == KeyBindings.KEY_OPTION)
			Main.closeGui();
	}

	public void onLeftClick(int screenX, int screenY, MouseEvent e) {
		for (Element el : guiElements) {
			el.onLeftClick(screenX, screenY,e);
		}
	}

	public void onRightClick(int screenX, int screenY, MouseEvent e) {
		for (Element el : guiElements) {
			el.onRightClick(screenX, screenY,e);
		}
	}

	public void render(Graphics2D g) {
		for (Element el : guiElements) {
			el.render(g);
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

	public void onMouseWheelMoved(int by, MouseWheelEvent e) {
		// NO-OP
	}

}
