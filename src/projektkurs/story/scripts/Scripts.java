package projektkurs.story.scripts;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.util.Logger;

public class Scripts {

	public static void example() {
		Main.getSpielfeld().spawn(
				new EntityItem(20, 20, new ItemStack(Items.ITEM_42)));
		Logger.info("Succesfully executed: Script 'example'");
	}

}
