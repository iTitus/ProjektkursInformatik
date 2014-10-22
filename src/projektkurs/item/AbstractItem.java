package projektkurs.item;

import java.awt.image.BufferedImage;

/**
 * Ein abstraktes Item
 * 
 */
public abstract class AbstractItem {

	/**
	 * Das Bild
	 * 
	 * @return
	 */
	public abstract BufferedImage getImage();

	/**
	 * Übersetzter Name
	 * 
	 * @return
	 */
	public abstract String getName();

	@Override
	public String toString() {
		return getName();
	}

}
