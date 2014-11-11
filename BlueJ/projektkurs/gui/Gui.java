package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.lib.Integers;

/**
 * Ein Graphical User Interface
 *
 */
public abstract class Gui {

	private static final Color backgroundColor = new Color(127, 127, 127, 156);

	public abstract void render(Graphics2D g);

	public void onLeftClick(int x, int y) {
		// NO-OP
	}

	public void onRightClick(int x, int y) {
		// NO-OP
	}

	public void onKeyPressed(int key) {
		// NO-OP
	}

	protected void drawDefaultBackground(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);
	}
}
