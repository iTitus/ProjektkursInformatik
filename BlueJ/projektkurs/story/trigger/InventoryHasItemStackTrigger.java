package projektkurs.story.trigger;

import projektkurs.Main;
import projektkurs.io.storage.SaveData;
import projektkurs.item.ItemStack;

/**
 * Prueft, ob sich ein bestimmter ItemStack im Spielerinventar befindet.
 */
public class InventoryHasItemStackTrigger extends AbstractTrigger {

	/**
	 * Der ItemStack.
	 */
	private ItemStack stack;

	/**
	 * Konstruktor.
	 *
	 * @param stack zu pruefender ItemStack.
	 */
	public InventoryHasItemStackTrigger(ItemStack stack) {
		this.stack = stack;
	}

	@Override
	public boolean isTriggerActive() {
		return Main.getPlayer().getInventory().getNumberOfItemsInInventory(stack) >= stack.getStackSize();
	}

	@Override
	public void write(SaveData data) {
		super.write(data);
		data.set("stack", stack.write());
	}

	@Override
	public void load(SaveData data) {
		super.load(data);
		stack = ItemStack.load(data.getSaveData("stack"));
	}
}
