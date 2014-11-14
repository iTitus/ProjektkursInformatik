package projektkurs.inventory;

import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.MathUtil;
import projektkurs.util.SaveData;

/**
 * Das Spielerinventar
 */
public class PlayerInventory extends Inventory {

	public static PlayerInventory load(SaveData data) {
		PlayerInventory inv = new PlayerInventory();

		inv.stacks = new ItemStack[data.getInteger(Strings.INV_SIZE)];

		for (int i = 0; i < inv.stacks.length; i++) {
			inv.setItemStackInSlot(i,
					ItemStack.load(data.getSaveData(Strings.INV_SLOT + i)));
		}

		inv.setSelectedItemStack(data.getInteger(Strings.INV_SELECTED));

		return inv;
	}

	private int selectedItemStack;

	/**
	 * @param size
	 */
	public PlayerInventory(int size) {
		this(size, -1);
	}

	/**
	 * @param size
	 * @param selectedItemStack
	 */
	public PlayerInventory(int size, int selectedItemStack) {
		super(size);
		this.selectedItemStack = selectedItemStack;
	}

	private PlayerInventory() {
		super();
	}

	/**
	 * @return
	 */
	public int getRelX() {
		return MathUtil.roundDiv(Integers.WINDOW_X, 2)
				- MathUtil.roundDiv(Integers.SLOT_SIZE * getSize(), 2);
	}

	/**
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

	@Override
	public SaveData write() {
		SaveData data = super.write();
		data.set(Strings.INV_SELECTED, selectedItemStack);
		return data;
	}
}
