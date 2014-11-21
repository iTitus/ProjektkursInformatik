package projektkurs.lib;

import java.awt.Toolkit;

/**
 * Sämtliche Zahlenkonstanten...
 */
public final class Integers {

    /**
     * Ticks, die der Cursor zum Blinken braucht.
     */
    public static final int CURSOR_BLINK_TIME      = 16;
    /**
     * Standard-Knopfhöhe.
     */
    public static final int DEFAULT_BUTTON_HEIGHT  = 64;
    /**
     * Standard-Knopfbreite.
     */
    public static final int DEFAULT_BUTTON_WIDTH   = 256;
    /**
     * X-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_X                 = 16;
    /**
     * Y-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_Y                 = 16;
    /**
     * Größe des Spielerinventars.
     */
    public static final int INVENTARGROESSE        = 7;
    /**
     * Größe des Kisteninventars.
     */
    public static final int KISTENGROESSE          = 3;
    public static final int NS_PER_SECOND          = 1000000000;
    public static final int NUKE_RADIUS            = 2;
    public static final int PLAYER_HEALTH          = 500;
    public static final int RASTER_SIZE            = 32;
    public static final int RPS                    = 5;
    public static final int RUN_AROUND_MOVE_CHANCE = 50;
    public static int       SIGHT_X                = Toolkit.getDefaultToolkit().getScreenSize().width / Integers.RASTER_SIZE - 2 * Integers.WINDOW_HUD_X
                                                           / Integers.RASTER_SIZE;
    public static int       SIGHT_Y                = Toolkit.getDefaultToolkit().getScreenSize().height / Integers.RASTER_SIZE - 2 * Integers.WINDOW_HUD_Y
                                                           / Integers.RASTER_SIZE - 1;
    public static final int SLOT_SIZE              = Integers.RASTER_SIZE + 2;
    public static final int STACK_SIZE_OFFSET      = 11;
    public static final int UPS                    = 20;
    public static final int WINDOW_HUD_X           = 34;
    public static final int WINDOW_HUD_Y           = 34;
    public static int       WINDOW_X               = RASTER_SIZE * SIGHT_X + 2 * WINDOW_HUD_X;
    public static int       WINDOW_Y               = RASTER_SIZE * SIGHT_Y + 2 * WINDOW_HUD_Y;

    /**
     * Verändert die Sichtfeldgröße.
     *
     * @param newSightX
     *            x
     * @param newSightY
     *            y
     */
    public static void changeSight(int newSightX, int newSightY) {
        SIGHT_X = newSightX;
        WINDOW_X = RASTER_SIZE * SIGHT_X + 2 * WINDOW_HUD_X;
        SIGHT_Y = newSightY;
        WINDOW_Y = RASTER_SIZE * SIGHT_Y + 2 * WINDOW_HUD_Y;
    }

    /**
     * Nicht instanziierbar.
     */
    private Integers() {
    }

}
