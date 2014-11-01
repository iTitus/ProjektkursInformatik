package projektkurs.lib;

/**
 * Sämtliche Zahlenkonstanten...
 */
public final class Integers {

	public static final int INVENTARGROESSE = 7;
	public static final int KISTENGROESSE = 3;

	public static final int RASTER_SIZE = 32;

	public static final int REACTION_TIME = 250;

	public static int SIGHT_X = 19;
	public static int SIGHT_Y = 19;

	public static final int TICK_TIME = 20;

	public static final int WINDOW_HUD_X = 32;
	public static final int WINDOW_HUD_Y = 32;

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

}
