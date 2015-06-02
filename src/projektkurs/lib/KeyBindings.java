package projektkurs.lib;

import java.awt.event.KeyEvent;

/**
 * KeyBindings (Integer-Konstanten).
 */
public final class KeyBindings {

    /**
     * Backspace-Buchstaben-Code.
     */
    public static final int BACK_SPACE = KeyEvent.VK_BACK_SPACE;
    /**
     * Backspace-Buchstaben.
     */
    public static final char BACK_SPACE_CHAR = '\b';
    /**
     * Return-Buchstaben-Code.
     */
    public static final int ENTER = KeyEvent.VK_ENTER;
    /**
     * Taste, um die Konsole zu oeffnen.
     */
    public static final int KEY_CONSOLE = KeyEvent.VK_C;
    /**
     * Taste, um runter zu gehen.
     */
    public static final int KEY_DOWN = KeyEvent.VK_S;
    /**
     * Taste, um das Inventar zu �ffnen.
     */
    public static final int KEY_INVENTORY = KeyEvent.VK_I;
    /**
     * Taste, um nach links zu gehen.
     */
    public static final int KEY_LEFT = KeyEvent.VK_A;
    /**
     * Taste, um die Menues/Optionen zu oeffnen oder GUIs zu schliessen.
     */
    public static final int KEY_MENU = KeyEvent.VK_ESCAPE;
    public static final int KEY_QUEST_LOG = KeyEvent.VK_Q;
    /**
     * Taste, um nach rechts zu gehen.
     */
    public static final int KEY_RIGHT = KeyEvent.VK_D;
    /**
     * Taste, um nach oben zu gehen.
     */
    public static final int KEY_UP = KeyEvent.VK_W;
    /**
     * Enter-Buchstaben.
     */
    public static final int LINE_BREAK_CHAR = '\n';
    /**
     * 'v'-Buchstaben-Code.
     */
    public static final int PASTE_KEY = KeyEvent.VK_V;
    /**
     * Tabulator-Buchstaben.
     */
    public static final int TABULATOR_CHAR = '\t';

    /**
     * Unbekannter-Buchstaben-Code (0xFFFF).
     */
    public static final int UNKNOWN_CHAR = (1 << 16) - 1;

    /**
     * Nicht instanziierbar.
     */
    private KeyBindings() {
    }

}
