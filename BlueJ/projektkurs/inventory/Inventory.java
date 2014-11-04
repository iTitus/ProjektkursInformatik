package projektkurs.inventory;

import projektkurs.item.ItemStack;

/**
 * Ein Inventarobjekt
 * 
 */
public class Inventory {

	protected ItemStack[] stacks;

	public Inventory(int size) {
		stacks = new ItemStack[size];
	}

	/**
	 * Ein neuer ItemStack wird dem Inventar hinzugefügt
	 * 
	 * @param newStack
	 *            ist der neue ItemStack
	 * @return success
	 */
	public boolean addItemStack(ItemStack newStack) {
		if (newStack != null) {
			for (int i = 0; i < stacks.length; i++) {
				if (getItemStackAt(i) == null) {
					stacks[i] = newStack;
					return true;
				} else if (stacks[i].itemAndDamageEquals(newStack)) {
					stacks[i].setStackSize(stacks[i].getStackSize()
							+ newStack.getStackSize());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ist der gegebene ItemStack im Inventar enthalten? - Streng
	 * 
	 * @param stack
	 * @return true, wenn ja; false, wenn nicht
	 */
	public boolean contains(ItemStack stack) {
		if (stack != null) {
			ItemStack item = null;
			for (int i = 0; i < stacks.length; i++) {
				item = stacks[i];
				if (item != null && item.stackEquals(stack))
					return true;
			}
		}
		return false;
	}

	/**
	 * Ist der gegebene ItemStack im Inventar enthalten? - Ignoriert die
	 * StackSize
	 * 
	 * @param stack
	 * @return true, wenn ja; false, wenn nicht
	 */
	public boolean containsIgnoreStackSize(ItemStack stack) {
		if (stack != null) {
			ItemStack item = null;
			for (int i = 0; i < stacks.length; i++) {
				item = stacks[i];
				if (item != null && item.itemAndDamageEquals(stack))
					return true;
			}
		}
		return false;
	}

	/**
	 * Verringert die StackSize eines ItemStacks an der gegebenen Stelle um die
	 * gegebene Anzahl und entfernt ihn, wenn die StackSize dann negativ ist
	 * 
	 * @param index
	 * @param stackSize
	 * @return success
	 */
	public boolean decrStackSize(int index, int stackSize) {

		ItemStack stack = getItemStackAt(index);

		if (stack != null) {
			stack.setStackSize(stack.getStackSize() - stackSize);
			if (stack.getStackSize() <= 0)
				removeItemStack(index);
			return true;
		}

		return false;

	}

	/**
	 * Return alle ItemStacks im Inventar
	 * 
	 * @return alle ItemStacks im Inventar
	 */
	public ItemStack[] getItems() {
		return stacks;
	}

	/**
	 * Returnt den ItemStack an der Stelle index
	 * 
	 * @param index
	 * @return ItemStack an der Stelle index
	 */
	public ItemStack getItemStackAt(int index) {
		if (index < 0 || index >= stacks.length)
			return null;
		return stacks[index];
	}

	/**
	 * Zahl der Items im Inventar
	 * 
	 * @return
	 */
	public int getNumberOfItemsInInventory() {
		int i = 0;
		for (int j = 0; j < stacks.length; j++) {
			if (getItemStackAt(j) != null)
				i += getItemStackAt(i).getStackSize();
		}
		return i;
	}

	/**
	 * Zahl der ItemStacks im Inventar
	 * 
	 * @return
	 */
	public int getNumberOfItemStacksInInventory() {
		int i = 0;
		for (int j = 0; j < stacks.length; j++) {
			if (getItemStackAt(j) != null)
				i++;
		}
		return i;
	}

	/**
	 * Größe des Inventars
	 * 
	 * @return
	 */
	public int getSize() {
		return stacks.length;
	}

	/**
	 * Erhöht die StackSize eines ItemStacks an der gegebenen Stelle um die
	 * gegebene Anzahl
	 * 
	 * @param index
	 * @param stackSize
	 * @return success
	 */
	public boolean incrStackSize(int index, int stackSize) {

		ItemStack stack = getItemStackAt(index);

		if (stack != null) {
			stack.setStackSize(stack.getStackSize() + stackSize);
			return true;
		}

		return false;

	}

	/**
	 * Ist das Inventar leer
	 * 
	 * @return true, wenn ja
	 */
	public boolean isInventoryEmpty() {
		for (int i = 0; i < stacks.length; i++) {
			if (getItemStackAt(i) != null)
				return false;
		}
		return true;

	}

	/**
	 * Ist das Inventar voll mit ItemStacks
	 * 
	 * @return true, wenn ja
	 */
	public boolean isInventoryFull() {
		for (int i = 0; i < stacks.length; i++) {
			if (getItemStackAt(i) == null)
				return false;
		}
		return true;

	}

	/**
	 * Entfernt einen ItemStack an der gegebenen Stelle aus dem Inventar
	 * 
	 * @param index
	 * @return success
	 */
	public boolean removeItemStack(int index) {
		if (getItemStackAt(index) != null) {
			stacks[index] = null;
			return true;
		}

		return false;
	}

	/**
	 * Entfernt den ItemStack, der an dem gegebenen ItemStack entspricht -
	 * Streng
	 * 
	 * @param stack
	 * @return success
	 */
	public boolean removeItemStack(ItemStack stackToRemove) {
		if (stackToRemove != null) {
			ItemStack stack = null;
			for (int i = 0; i < stacks.length; i++) {
				stack = getItemStackAt(i);
				if (stack != null && stack.stackEquals(stackToRemove)) {
					stacks[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Entfernt den ItemStack, der an dem gegebenen ItemStack entspricht -
	 * Ignoriert StackSize
	 * 
	 * @param stack
	 * @return success
	 */
	public boolean removeItemStackIgnoreStackSize(ItemStack stackToRemove) {
		if (stackToRemove != null) {
			ItemStack stack = null;
			for (int i = 0; i < stacks.length; i++) {
				stack = getItemStackAt(i);
				if (stack != null && stack.itemAndDamageEquals(stackToRemove)) {
					stacks[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Setzt den gegebenen ItemStack an die gegebene Stelle im Inventar
	 * 
	 * @param index
	 * @param stack
	 * @return success
	 */
	public boolean setItemStackInSlot(int index, ItemStack stack) {
		if (index >= 0 && index < stacks.length) {
			stacks[index] = stack;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {

		String s = "Inventory";

		if (getSize() > 0) {
			ItemStack stack = null;
			s += "[";
			stack = getItemStackAt(0);
			s += (stack != null ? stack.getName() : "");

			if (getSize() > 1) {
				for (int i = 1; i < stacks.length; i++) {
					stack = getItemStackAt(i);
					if (stack != null)
						s += ", " + stack.getName();
				}
			}

			s += "]";
		} else {
			s += " - EMPTY";
		}

		return s;
	}

}
