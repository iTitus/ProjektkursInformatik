package projektkurs.item;

import projektkurs.util.I18n;

/**
 * Organisiert alle Gegenstaende
 * 
 */
public enum Item {

	ITEM_42("item.42.name"), KEY("item.key.name"), NUKE("item.nuke.name");

	private String internalName;

	private Item(String internalName) {
		this.internalName = internalName;
	}

	/**
	 * Übersetzter Name
	 * 
	 * @return
	 */
	public String getName() {
		return I18n.getString(internalName);
	}

}
