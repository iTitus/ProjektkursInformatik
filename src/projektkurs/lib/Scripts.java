package projektkurs.lib;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.util.I18n;

/**
 * Diverse Skripte.
 */
public final class Scripts {

    /**
     * Startet CutScene No. 1 und ändert das Spielfeld.
     */
    public static void cutSceneOne() {
        CutSceneManager.startCutScene(CutScenes.cutSceneOne);
        switchMap(1);
    }

    /**
     * Verliert das Spiel.
     */
    public static void loose() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.meltdown"), I18n.getString("description.meltdown"), 0, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(Images.nuke), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    /**
     * Wechselt das Spielfeld und setzt den Spieler an die Position 1, 1.
     *
     * @param i
     *            Index
     */
    public static void switchMap(Integer i) {
        Main.getLevel().setMap(i);
        Main.getPlayer().setPos(1, 1);
    }

    /**
     * Gewinnt das Spiel.
     */
    public static void win() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.win"), I18n.getString("description.win"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(
                Images.finish), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    /**
     * Nicht instanziierbar.
     */
    private Scripts() {
    }
}
