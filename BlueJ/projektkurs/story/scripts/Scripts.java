package projektkurs.story.scripts;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.util.Logger;

public class Scripts {

	public static void example() {
		Main.getSpielfeld().spawn(
				new EntityItem(20, 20, new ItemStack(Items.ITEM_42)));
		Logger.info("Succesfully executed: Script 'example'");
	}

	public static void looseGame() {
		Main.pause();
		JOptionPane.showMessageDialog(null, "You loose!", "Nuclear meltdown!",
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.nuke));
		Main.exit();
	}

}
