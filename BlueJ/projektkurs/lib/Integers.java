package projektkurs.lib;

import java.awt.Toolkit;

/**
 * Saemtliche Zahlenkonstanten...
 */
public final class Integers {

    /**
     * Groesse des Kisteninventars.
     */
    public static final int CHEST_SIZE = 3;
    /**
     * Ticks, die der Cursor zum Blinken braucht.
     */
    public static final int CURSOR_BLINK_TIME = 16;
    /**
     * Standard-Knopfhoehe.
     */
    public static final int DEFAULT_BUTTON_HEIGHT = 64;
    /**
     * Standard-Knopfbreite.
     */
    public static final int DEFAULT_BUTTON_WIDTH = 256;
    /**
     * Standard-Schriftgroesse.
     */
    public static final int DEFAULT_FONT_SIZE = 24;
    /**
     * X-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_X = 24;
    /**
     * Y-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_Y = 12;
    /**
     * Maximale Schriftgroesse.
     */
    public static final int MAX_FONT_SIZE = 64;
    /**
     * Maximale Anzahl an Items.
     */
    public static final int MAX_ITEM_NUMBER = 255;
    /**
     * Maximale Anzahl an Rastern.
     */
    public static final int MAX_RASTER_NUMBER = 255;
    /**
     * Updates pro Bewegungsupdate.
     */
    public static final int MPU = 4;
    /**
     * Nanosekunden in einer Sekunde.
     */
    public static final int NS_PER_SECOND = 1000000000;
    /**
     * Explosionsradius einer Atombombe.
     */
    public static final int NUKE_RADIUS = 2;
    /**
     * Spielergesundheit.
     */
    public static final int PLAYER_HEALTH = 500;
    /**
     * Groesse des Spielerinventars.
     */
    public static final int PLAYER_INVENTORY_SIZE = 7;
    /**
     * Rastergroesse in Pixeln.
     */
    public static final int RASTER_SIZE = 32;
    /**
     * Chance, dass sich ein Entity mit dem Run-Around Verhalten bewegt.
     */
    public static final int RUN_AROUND_MOVE_CHANCE = 50;
    /**
     * Breite des Sichtfeldes in Rastern.
     */
    public static int sightX = Toolkit.getDefaultToolkit().getScreenSize().width / Integers.RASTER_SIZE - 2 * Integers.WINDOW_HUD_X / Integers.RASTER_SIZE;
    /**
     * Hoehe des Sichtfeldes in Rastern.
     */
    public static int sightY = Toolkit.getDefaultToolkit().getScreenSize().height / Integers.RASTER_SIZE - 2 * Integers.WINDOW_HUD_Y / Integers.RASTER_SIZE - 1;
    /**
     * Die Groesse eines Inventarslots in Pixeln.
     */
    public static final int SLOT_SIZE = Integers.RASTER_SIZE + 2;
    /**
     * Verschiebung der Groesse eines ItemStacks beim Inventarrendern.
     */
    public static final int STACK_SIZE_OFFSET = 11;
    /**
     * Transparenter Pixel.
     */
    public static final int TRANSPARENCY = 0xFF00FF;
    /**
     * Updates pro Sekunde.
     */
    public static final int UPS = 20;
    public static final int WILDCARD_VALUE = Integer.MAX_VALUE;
    /**
     * Die Breite des Fensterrandes.
     */
    public static final int WINDOW_HUD_X = 34;
    /**
     * Die Hoehe des Fensterrandes.
     */
    public static final int WINDOW_HUD_Y = 34;
    /**
     * Fensterbreite in Pixeln.
     */
    public static int windowX = RASTER_SIZE * sightX + 2 * WINDOW_HUD_X;
    /**
     * Fensterhoehe in Pixeln.
     */
    public static int windowY = RASTER_SIZE * sightY + 2 * WINDOW_HUD_Y;

    /**
     * Veraendert die Sichtfeldgroesse.
     *
     * @param newSightX
     *            x
     * @param newSightY
     *            y
     */
    public static void changeSight(int newSightX, int newSightY) {
        sightX = newSightX;
        windowX = RASTER_SIZE * sightX + 2 * WINDOW_HUD_X;
        sightY = newSightY;
        windowY = RASTER_SIZE * sightY + 2 * WINDOW_HUD_Y;
    }

    /**
     * Nicht instanziierbar.
     */
    private Integers() {
    }

}
