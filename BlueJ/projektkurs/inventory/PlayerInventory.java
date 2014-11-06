package projektkurs.inventory;

import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

/**
 * Das Spielerinventar
 *
 */
public class PlayerInventory extends Inventory {

	private int selectedItemStack;

	/**
	 * 
	 * @param size
	 */
	public PlayerInventory(int size) {
		this(size, -1);
	}

	/**
	 * 
	 * @param size
	 * @param selectedItemStack
	 */
	public PlayerInventory(int size, int selectedItemStack) {
		super(size);
		this.selectedItemStack = selectedItemStack;
	}

	/**
	 * 
	 * @return
	 */
	public int getRelX() {
		return MathUtil.ceilDiv(Integers.WINDOW_X, 2)
				- MathUtil.ceilDiv(Integers.SLOT_SIZE * getSize(), 2);
	}

	/**
	 * 
	 * @return
	 */
	public int getRelY() {
		return Integers.WINDOW_Y - Integers.WINDOW_HUD_Y;
	}

	/**
	 * Returnt die Stelle des ausgewählten ItemStack
	 * 
	 * @return
	 */
	public int getSelectedIndex() {
		return selectedItemStack;
	}

	/**
	 * Returnt den ausgewählten ItemStack
	 * 
	 * @return
	 */
	public ItemStack getSelectedItemStack() {
		return getItemStackAt(selectedItemStack);
	}

	/**
	 * Ist ein ItemStack ausgewählt?
	 * 
	 * @return
	 */
	public boolean hasItemStackSelected() {
		return selectedItemStack < 0;
	}

	/**
	 * Wählt den ItemStack im Slot index aus, -1, um keinen ItemStack
	 * auszuwählen
	 * 
	 * @param index
	 */
	public void setSelectedItemStack(int index) {
		if (index < 0 || index >= stacks.length)
			selectedItemStack = -1;
		else
			selectedItemStack = index;
	}

}
