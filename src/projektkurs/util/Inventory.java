package projektkurs.util;

import projektkurs.item.ItemStack;

/**
 * Ein Inventarobjekt
 * 
 */
public class Inventory {

	private ItemStack[] items;

	public Inventory(int inventargroesse) {
		items = new ItemStack[inventargroesse];
	}

	/**
	 * Ein Gegendstand wird zum Inventar hinzugefügt
	 * 
	 * @param neuerGegenstand
	 *            ist der neue Gegenstand
	 * @return success
	 */
	public boolean addItem(ItemStack newItem) {

		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) == null) {
				items[i] = newItem;
				return true;
			} else if (items[i].itemAndDamageEquals(newItem)) {
				items[i].setStackSize(items[i].getStackSize()
						+ newItem.getStackSize());
				return true;
			}

		}

		return false;

	}

	/**
	 * Streng
	 * 
	 * @param stack
	 * @return
	 */
	public boolean contains(ItemStack stack) {
		ItemStack item = null;
		for (int i = 0; i < items.length; i++) {
			item = items[i];
			if (item != null && item.stackEquals(stack))
				return true;
		}

		return false;
	}

	/**
	 * Ignoriert StackSize
	 * 
	 * @param stack
	 * @return
	 */
	public boolean containsIgnoreStackSize(ItemStack stack) {
		ItemStack item = null;
		for (int i = 0; i < items.length; i++) {
			item = items[i];
			if (item != null && item.itemAndDamageEquals(stack))
				return true;
		}

		return false;
	}

	/**
	 * 
	 * @param index
	 * @param stackSize
	 * @return success
	 */
	public boolean decrStackSize(int index, int stackSize) {

		ItemStack stack = getItemAt(index);

		if (stack != null) {
			stack.setStackSize(stack.getStackSize() - stackSize);
			if (stack.getStackSize() <= 0)
				removeItem(index);
			return true;
		}

		return false;

	}

	/**
	 * Item an der Stelle index
	 * 
	 * @param index
	 * @return Item an der Stelle index
	 */
	public ItemStack getItemAt(int index) {
		if (index < 0 || index >= items.length)
			return null;
		return items[index];
	}

	/**
	 * 
	 * @return all items
	 */
	public ItemStack[] getItems() {
		return items;
	}

	/**
	 * Zahl der Items im Inventar
	 * 
	 * @return
	 */
	public int getNumberOfItemsInInventory() {
		int i = 0;
		for (int j = 0; j < items.length; j++) {
			if (getItemAt(j) != null)
				i += getItemAt(i).getStackSize();
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
		for (int j = 0; j < items.length; j++) {
			if (getItemAt(j) != null)
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
		return items.length;
	}

	/**
	 * 
	 * @param index
	 * @param stackSize
	 * @return success
	 */
	public boolean incrStackSize(int index, int stackSize) {

		ItemStack stack = getItemAt(index);

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
		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) != null)
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
		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) == null)
				return false;
		}
		return true;

	}

	/**
	 * Ein Gegenstand wird aus dem Inventar entfernt
	 * 
	 * @param Gegenstandstelle
	 *            , welcher Gegenstand soll entfernt werden
	 * @return success
	 */
	public boolean removeItem(int index) {
		if (getItemAt(index) != null) {
			items[index] = null;
			sort();
			return true;
		}

		return false;
	}

	/**
	 * Ein Gegenstand wird aus dem Inventar entfernt - Streng
	 * 
	 * @param item
	 *            Item, das entfernt wird
	 * @return success
	 */
	public boolean removeItem(ItemStack item) {

		ItemStack stack = null;

		for (int i = 0; i < items.length; i++) {
			stack = getItemAt(i);
			if (stack != null && stack.stackEquals(item)) {
				items[i] = null;
				sort();
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param index
	 * @param stack
	 * @return success
	 */
	public boolean setStackInSlot(int index, ItemStack stack) {
		if (index >= 0 && index < items.length) {
			items[index] = stack;
			if (stack == null)
				sort();
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
			stack = getItemAt(0);
			s += (stack != null ? stack.getName() : "");

			if (getSize() > 1) {
				for (int i = 1; i < items.length; i++) {
					stack = getItemAt(i);
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

	/**
	 * Entfernt leere Stellen - zur besseren Übersichtlichkeit
	 */
	private void sort() {
		ItemStack[] newItems = new ItemStack[items.length];

		int j = 0;
		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) != null) {
				newItems[j] = getItemAt(i);
				j++;
			}
		}

		items = newItems;
	}
}
