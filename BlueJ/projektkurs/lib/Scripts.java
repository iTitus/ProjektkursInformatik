package projektkurs.lib;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.util.I18n;

/**
 * Diverse Skripte.
 */
public final class Scripts {

    /**
     * Verliert das Spiel.
     */
    public static void loose() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.loose"), I18n.getString("description.loose"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.nuke), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    /**
     * Gewinnt das Spiel.
     */
    public static void win() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.win"), I18n.getString("description.win"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.finish), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    /**
     * Nicht instanziierbar.
     */
    private Scripts() {
    }
}
