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
     * Taste, um die Konsole zu öffnen.
     */
    public static final int KEY_CONSOLE = KeyEvent.VK_C;
    /**
     * Taste, um runter zu gehen.
     */
    public static final int KEY_DOWN = KeyEvent.VK_S;
    /**
     * Taste, um nach links zu gehen.
     */
    public static final int KEY_LEFT = KeyEvent.VK_A;
    /**
     * Taste, um die Menüs/Optionen zu öffnen oder GUIs zu schließen.
     */
    public static final int KEY_MENU = KeyEvent.VK_ESCAPE;
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
