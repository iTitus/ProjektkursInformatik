package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;

/**
 * Ein Graphical User Interface
 *
 */
public abstract class Gui {

	private static final Color backgroundColor = new Color(127, 127, 127, 156);

	public void render(Graphics2D g) {
		// NO-OP
	}

	public void onLeftClick(int x, int y) {
		// NO-OP
	}

	public void onRightClick(int x, int y) {
		// NO-OP
	}

	public void onKeyPressed(int key) {
		if (key == KeyBindings.KEY_EXIT)
			Main.closeGui();
	}

	protected void drawDefaultBackground(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y,
				Integers.SIGHT_X * Integers.RASTER_SIZE, Integers.SIGHT_Y
						* Integers.RASTER_SIZE);
	}
}
