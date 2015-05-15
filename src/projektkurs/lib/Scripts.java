package projektkurs.lib;

import java.lang.reflect.Method;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.dialog.Dialog;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityFerryhouse;
import projektkurs.entity.EntityFisher;
import projektkurs.entity.EntityFisherboat;
import projektkurs.entity.EntityItem;
import projektkurs.item.ItemStack;
import projektkurs.story.trigger.AbstractTrigger;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.InvertedTrigger;
import projektkurs.util.I18n;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Diverse Skripte.
 */
public final class Scripts {

	public static final Method REMOVE_ENTITY = ReflectionUtil.getMethod(Scripts.class, "removeFerryHouse", EntityFerryhouse.class, Spielfeld.class);
	public static final Method SPAWN_ENTITY = ReflectionUtil.getMethod(Scripts.class, "spawnFerryHouse", EntityFerryhouse.class, Spielfeld.class);

	public static boolean thrashCan = false, woman = false, junge = false, fisher = false, cutsceneTwo = false;

	/**
	 * Nicht instanziierbar.
	 */
	private Scripts() {
	}

	public static void cutsceneOne() {
		CutSceneManager.startCutScene(CutScenes.one);
	}

	public static void cutscenestart(CutScene c, Spielfeld map, AbstractTrigger t) {
		CutSceneManager.startCutScene(c);
		map.getStoryManager().removeTrigger(t);
	}

	public static void cutsceneThree() {
		CutSceneManager.startCutScene(CutScenes.three);
	}

	public static void cutscenetwo() {
		if (!cutsceneTwo) {
			CutSceneManager.startCutScene(CutScenes.two);
			Main.getLevel().getMap().spawn(new EntityFisherboat(Main.getLevel().getMap(), 61, 40));
			Main.getLevel().getMap().spawn(new EntityFisher(Main.getLevel().getMap(), 61, 47));
			cutsceneTwo = true;
		}
	}

	public static void dialogstart(Dialog d, Entity e) {
		Main.getLevel().getDialogManager().startDialog(d, e);
	}

	/**
	 * Verliert das Spiel.
	 */
	public static void loose() {
		Main.pause();
		JOptionPane.showOptionDialog(null, I18n.getString("description.loose"), I18n.getString("description.loose"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(Sprites.nuke.toBufferedImage()), new Object[]{I18n.getString("button.exit")}, null);
		Main.exit();
	}

	public static void removeFerryHouse(EntityFerryhouse e, Spielfeld map) {
		map.deSpawn(e);
		InvertedTrigger invtrig = new InvertedTrigger(new AreaTrigger(20, 21, 13, 14));
		map.getStoryManager().registerTrigger(invtrig, SPAWN_ENTITY, e, map);
	}

	/**
	 * Entfernt ein Item aus dem Inventar des Spielers
	 *
	 * @param r Der Index des zu enfernenden Items
	 */
	public static void removeItem(ItemStack r) {
		Main.getPlayer().getInventory().removeItemStack(r);
	}

	public static void scriptFisher() {
		if (fisher == false) {

			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 60, 48, new ItemStack(Items.chewingGum, 1, 100)));
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 60, 49, new ItemStack(Items.fishnet, 1, 100)));
			Main.getLevel().getMap().getStoryManager().registerTrigger(new AreaTrigger(58, 68, 6, 1), ReflectionUtil.getMethod(Scripts.class, "setSwitchMapTrigger", Integer.TYPE, AbstractTrigger.class, AbstractTrigger.class), 1, new AreaTrigger(58, 68, 6, 1), new AreaTrigger(58, 69, 6, 1));
			fisher = true;
		}
	}

	public static void scriptJunge() {
		if (Main.getPlayer().getInventory().containsIgnoreStackSize(new ItemStack(Items.yoyoFixed)) == true) {
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 32, 51, new ItemStack(Items.stoneCatapult)));
			Main.getPlayer().getInventory().removeItemStack(new ItemStack(Items.yoyoFixed));
			junge = true;
		}
	}

	public static void scriptNest() {
		if (Main.getPlayer().getInventory().containsIgnoreStackSize(new ItemStack(Items.stoneCatapultwithStone)) == true) {

			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 7, 61, new ItemStack(Items.teddydefault, 1, 100)));
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 6, 61, new ItemStack(Items.stoneCatapult, 1, 100)));
			Main.getPlayer().getInventory().removeItemStack(new ItemStack(Items.stoneCatapultwithStone));
		}
	}

	public static void scriptThrashCan() {
		if (thrashCan == false) {
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 45, 22, new ItemStack(Items.stone, 1, 100)));
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 44, 22, new ItemStack(Items.knife, 2, 100)));
			thrashCan = true;
		}

	}

	public static void scriptWitchCoul() {
		if (Main.getPlayer().getInventory().containsIgnoreStackSize(new ItemStack(Items.teddyVoodoo)) == true) {
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 46, 47, new ItemStack(Items.gramophoneItem)));
			Main.getPlayer().getInventory().removeItemStack(new ItemStack(Items.teddyVoodoo));
		}
	}

	public static void scriptWoman() {
		if (woman == false) {
			Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), 15, 26, new ItemStack(Items.earrings, 1, 100)));
			woman = true;
		}
	}

	public static void setSpawn(int x, int y) {
		Main.getLevel().getMap().setSpawn(x, y);
	}

	public static void setSwitchMapTrigger(int i, AbstractTrigger t) {
		Main.getLevel().getMap().getStoryManager().registerTrigger(t, ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE), i);
	}

	/**
	 * Trigger ab wo die Map geaendert wird
	 *
	 * @param i  map
	 * @param t1 Stelle des SwitchMapTrigger
	 * @param t2 Stelle von SwitchMap
	 */

	public static void setSwitchMapTrigger(int i, AbstractTrigger t1, AbstractTrigger t2) {
		Main.getLevel().getMap().getStoryManager().registerTrigger(t2, ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE, AbstractTrigger.class, AbstractTrigger.class), i, t1, t2);
	}

	public static void spawnFerryHouse(EntityFerryhouse e, Spielfeld map) {
		map.spawn(e);
		AreaTrigger area = new AreaTrigger(20, 21, 13, 14);
		map.getStoryManager().registerTrigger(area, REMOVE_ENTITY, e, map);
	}

	/**
	 * Spawnt ein Item an einer Positon
	 *
	 * @param posx x-Position des Items
	 * @param posy y-Position des Items
	 * @param i    Zu spawnender ItemStack
	 */
	public static void spawnItem(int posx, int posy, ItemStack i) {
		Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), posx, posy, i));
	}

	/**
	 * Nimmt einen Gegenstand aus dem Inventar und spawnt einen anderen
	 *
	 * @param posx
	 * @param posy
	 * @param i    zu spawnendes Item
	 * @param r    zu entfernendes Item
	 */
	public static void spawnItemRemoveItem(int posx, int posy, ItemStack i, ItemStack r) {
		Main.getLevel().getMap().spawn(new EntityItem(Main.getLevel().getMap(), posx, posy, i));
		Main.getPlayer().getInventory().removeItemStack(r);
	}

	public static void switchMap(int i) {
		Main.getLevel().setMap(i);
	}

	/**
	 * Aendert das Spielfeld.
	 *
	 * @param i  Spielfeld
	 * @param t1 Uebergangstrigger
	 * @param t2 Trigger fuer Ubergangstrigger
	 */
	public static void switchMap(int i, AbstractTrigger t1, AbstractTrigger t2) {
		Main.getLevel().getMap().getStoryManager().registerTrigger(t1, ReflectionUtil.getMethod(Scripts.class, "setSwitchMapTrigger", Integer.TYPE, AbstractTrigger.class, AbstractTrigger.class), i, t1, t2);
		Main.getLevel().setMap(i);
	}

	/**
	 * Gewinnt das Spiel.
	 */
	public static void win() {
		Main.pause();
		JOptionPane.showOptionDialog(null, I18n.getString("description.win"), I18n.getString("description.win"), 0, JOptionPane.ERROR_MESSAGE, new ImageIcon(Sprites.finish.toBufferedImage()), new Object[]{I18n.getString("button.exit")}, null);
		Main.exit();
	}
}
