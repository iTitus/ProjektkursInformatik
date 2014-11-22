package projektkurs.lib;

import java.awt.event.KeyEvent;

/**
 * KeyBindings (Integer-Konstanten).
 */
public final class KeyBindings {

    /**
     * Backspace-Buchstaben-Code.
     */
    public static final int BACK_SPACE = '\b';
    /**
     * Taste, um runter zu gehen.
     */
    public static final int KEY_DOWN   = KeyEvent.VK_S;
    /**
     * Taste, um nach links zu gehen.
     */
    public static final int KEY_LEFT   = KeyEvent.VK_A;
    /**
     * Taste, um die Optionen zu öffnen oder GUIs zu schließen.
     */
    public static final int KEY_OPTION = KeyEvent.VK_ESCAPE;
    /**
     * Taste, um nach rechts zu gehen.
     */
    public static final int KEY_RIGHT  = KeyEvent.VK_D;
    /**
     * Taste, um nach oben zu gehen.
     */
    public static final int KEY_UP     = KeyEvent.VK_W;
    /**
     * Enter-Buchstaben-Code.
     */
    public static final int LINE_BREAK = '\n';
    /**
     * 'v'-Buchstaben-Code.
     */
    public static final int PASTE_KEY  = 22;
    /**
     * Tabulator-Buchstaben-Code.
     */
    public static final int TABULATOR  = '\t';

    /**
     * Nicht instanziierbar.
     */
    private KeyBindings() {
    }

}
