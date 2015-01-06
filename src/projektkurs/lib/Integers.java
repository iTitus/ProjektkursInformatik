package projektkurs.lib;

import java.awt.Toolkit;

/**
 * Sämtliche Zahlenkonstanten...
 */
public final class Integers {

    /**
     * Größe des Kisteninventars.
     */
    public static final int CHEST_SIZE = 3;
    /**
     * Ticks, die der Cursor zum Blinken braucht.
     */
    public static final int CURSOR_BLINK_TIME = 16;
    /**
     * Standard-Knopfhöhe.
     */
    public static final int DEFAULT_BUTTON_HEIGHT = 64;
    /**
     * Standard-Knopfbreite.
     */
    public static final int DEFAULT_BUTTON_WIDTH = 256;
    /**
     * Standard-Schriftgröße.
     */
    public static final int DEFAULT_FONT_SIZE = 24;
    /**
     * X-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_X = 16;
    /**
     * Y-Bildschirmkoordinate der Informationen.
     */
    public static final int INFO_Y = 16;
    /**
     * Maximale Schriftgröße.
     */
    public static final int MAX_FONT_SIZE = 64;
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
     * Größe des Spielerinventars.
     */
    public static final int PLAYER_INVENTORY_SIZE = 7;
    /**
     * Rastergröße in Pixeln.
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
     * Höhe des Sichtfeldes in Rastern.
     */
    public static int sightY = Toolkit.getDefaultToolkit().getScreenSize().height / Integers.RASTER_SIZE - 2 * Integers.WINDOW_HUD_Y / Integers.RASTER_SIZE - 1;
    /**
     * Die Größe eines Inventarslots in Pixeln.
     */
    public static final int SLOT_SIZE = Integers.RASTER_SIZE + 2;
    /**
     * Verschiebung der Größe eines ItemStacks beim Inventarrendern.
     */
    public static final int STACK_SIZE_OFFSET = 11;
    /**
     * Updates pro Sekunde.
     */
    public static final int UPS = 20;
    /**
     * Die Breite des Fensterrandes.
     */
    public static final int WINDOW_HUD_X = 34;
    /**
     * Die Höhe des Fensterrandes.
     */
    public static final int WINDOW_HUD_Y = 34;
    /**
     * Fensterbreite in Pixeln.
     */
    public static int windowX = RASTER_SIZE * sightX + 2 * WINDOW_HUD_X;
    /**
     * Fensterhöhe in Pixeln.
     */
    public static int windowY = RASTER_SIZE * sightY + 2 * WINDOW_HUD_Y;

    /**
     * Verändert die Sichtfeldgröße.
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
