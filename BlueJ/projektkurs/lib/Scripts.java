package projektkurs.lib;

import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.dialog.DialogManager;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityFerryhouse;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.InvertedTrigger;
import projektkurs.story.trigger.Trigger;
import projektkurs.util.I18n;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Diverse Skripte.
 */
public final class Scripts {

    public static final Method REMOVE_ENTITY = ReflectionUtil.getMethod(Scripts.class, "removeFerryHouse", EntityFerryhouse.class, Spielfeld.class);
    public static final Method SPAWN_ENTITY = ReflectionUtil.getMethod(Scripts.class, "spawnFerryHouse", EntityFerryhouse.class, Spielfeld.class);

    public static void cutscenestart(CutScene c, Spielfeld map, Trigger t) {
        CutSceneManager.startCutScene(c);
        map.getStoryManager().removeTrigger(t);
    }

    public static void dialogstart(projektkurs.dialog.Dialog d, Entity e) {
        DialogManager.startDialog(d, e);
    }

    /**
     * Verliert das Spiel.
     */
    public static void loose() {
        Main.pause();
        JOptionPane.showOptionDialog(null, I18n.getString("description.loose"), I18n.getString("description.loose"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.nuke), new Object[] { I18n.getString("button.exit") }, null);
        Main.exit();
    }

    public static void removeFerryHouse(EntityFerryhouse e, Spielfeld map) {
        map.deSpawn(e);
        InvertedTrigger invtrig = new InvertedTrigger(new AreaTrigger(20, 21, 13, 14));
        map.getStoryManager().registerTrigger(invtrig, SPAWN_ENTITY, e, map);
    }

    public static void spawnFerryHouse(EntityFerryhouse e, Spielfeld map) {
        map.spawn(e);
        AreaTrigger area = new AreaTrigger(20, 21, 13, 14);
        map.getStoryManager().registerTrigger(area, REMOVE_ENTITY, e, map);
    }

    /**
     * Aendert das Spielfeld.
     *
     * @param i
     *            Spielfeld
     */
    public static void switchMap(int i) {
        Main.getLevel().setMap(i);
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
