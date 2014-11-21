package projektkurs.story.script;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.Images;
import projektkurs.story.cutscene.CutSceneOne;
import projektkurs.util.I18n;

/**
 * Diverse Skripte
 */
public class Scripts {

    public static void cutSceneOne() {
        CutSceneManager.startCutScene(CutSceneOne.cutSceneOne());
        switchMap(1);
    }

    public static void loose() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.meltdown"), I18n.getString("description.meltdown"), 0, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(Images.nuke), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    public static void switchMap(Integer i) {
        Main.getLevel().setMap(i);
        Main.getPlayer().setPos(1, 1);
    }

    public static void win() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.win"), I18n.getString("description.win"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(
                Images.finish), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }
}
