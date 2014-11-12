package projektkurs.item;

import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;

/**
 * Ein abstraktes Item
 */
public abstract class AbstractItem {

	/**
	 * Das Bild
	 *
	 * @return
	 */
	public abstract BufferedImage getImage();

	public BufferedImage getImage(ItemStack stack) {
		return getImage();
	}

	/**
	 * Übersetzter Name
	 *
	 * @return
	 */
	public abstract String getName();

	public void onLeftClick(Entity e, ItemStack stack) {
		// NO-OP
	}

	public void onRightClick(Entity e, ItemStack stack) {
		// NO-OP
	}

	@Override
	public String toString() {
		return getName();
	}
}
