package projektkurs.story.scripts;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.util.I18n;
import projektkurs.util.Logger;
import projektkurs.world.raster.Raster;

public class Scripts {

	public static void example() {
		Main.getSpielfeld().spawn(
				new EntityItem(20, 20, new ItemStack(Items.ITEM_42)));
		Logger.info("Succesfully executed: Script 'example'");
	}

	public static void looseGame() {
		Main.pause();
		JOptionPane.showOptionDialog(null,
				I18n.getString("description.meltdown"),
				I18n.getString("description.meltdown"), 0,
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.nuke),
				new Object[] { I18n.getString("button.exit") }, null);
		Main.exit();
	}

	public static void treeBomb() {
		Main.getSpielfeld().setRasterAt(20, 20, Raster.BAUM);
	}

	public static void win() {
		Main.pause();
		JOptionPane.showOptionDialog(null, I18n.getString("description.win"),
				I18n.getString("description.win"), 0,
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.finish),
				new Object[] { I18n.getString("button.exit") }, null);
		Main.exit();
	}
}
