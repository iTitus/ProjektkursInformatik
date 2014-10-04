package projektkurs.lib;

import projektkurs.item.Item;

/**
 * Ein Inventarobjekt
 * 
 */
public class Inventory {

	private Item[] items;

	public Inventory(int inventargroesse) {
		items = new Item[inventargroesse];
	}

	/**
	 * Ein Gegendstand wird zum Inventar hinzugefügt
	 * 
	 * @param neuerGegenstand
	 *            ist der neue Gegenstand
	 * @return success
	 */
	public boolean addItem(Item newItem) {

		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) == null) {
				items[i] = newItem;
				return true;
			}

		}

		return false;

	}

	/**
	 * Item an der Stelle index
	 * 
	 * @param index
	 * @return Item an der Stelle index
	 */
	public Item getItemAt(int index) {
		if (index < 0 || index >= items.length)
			return null;
		return items[index];
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
	 * ist das inventar leer
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
	 * ist das inventar voll
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
	 * Ein Gegenstand wird aus dem Inventar entfernt
	 * 
	 * @param itemType
	 *            , welches Item entfernt wird
	 * @return success
	 */
	public boolean removeItem(Item itemType) {

		for (int i = 0; i < items.length; i++) {
			if (getItemAt(i) == itemType) {
				items[i] = null;
				sort();
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {

		String s = "Inventory";

		if (getSize() > 0) {
			s += "[";
			s += getItemAt(0).getName() + "(" + getItemAt(0) + ")";

			if (getSize() > 1) {
				for (int i = 1; i < items.length; i++) {
					s += ", " + getItemAt(i).getName() + "(" + getItemAt(i)
							+ ")";
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
		Item[] newItems = new Item[items.length];

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
