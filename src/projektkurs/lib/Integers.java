package projektkurs.lib;

import java.awt.Toolkit;

/**
 * Sämtliche Zahlenkonstanten...
 */
public final class Integers {

	public static final int INVENTARGROESSE = 7;
	public static final int KISTENGROESSE = 3;

	public static final int RASTER_SIZE = 32;

	public static int SIGHT_X = (Toolkit.getDefaultToolkit().getScreenSize().width / Integers.RASTER_SIZE)
			- ((2 * Integers.WINDOW_HUD_X) / Integers.RASTER_SIZE);

	public static int SIGHT_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / Integers.RASTER_SIZE)
			- ((2 * Integers.WINDOW_HUD_Y) / Integers.RASTER_SIZE) - 1;
	public static final int SLOT_SIZE = Integers.RASTER_SIZE + 2;

	public static final int FPS = 60;
	public static final int UPS = 60;
	public static final int RPS = 5;

	public static final int WINDOW_HUD_X = 34;
	public static final int WINDOW_HUD_Y = 34;

	public static int WINDOW_X = RASTER_SIZE * SIGHT_X + 2 * WINDOW_HUD_X;
	public static int WINDOW_Y = RASTER_SIZE * SIGHT_Y + 2 * WINDOW_HUD_Y;

	/**
	 * Verändert die Sichtfeldgröße
	 * 
	 * @param newSightX
	 * @param newSightY
	 */
	public static void changeSight(int newSightX, int newSightY) {
		SIGHT_X = newSightX;
		WINDOW_X = RASTER_SIZE * SIGHT_X + 2 * WINDOW_HUD_X;
		SIGHT_Y = newSightY;
		WINDOW_Y = RASTER_SIZE * SIGHT_Y + 2 * WINDOW_HUD_Y;
	}

	private Integers() {
	}

}
