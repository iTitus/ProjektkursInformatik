package projektkurs.item;

import java.awt.image.BufferedImage;

import projektkurs.util.I18n;

public class BaseItem extends AbstractItem {

	private final BufferedImage image;
	private final String name;

	public BaseItem(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public String getName() {
		return I18n.getString(String.format("item.%s.name", name));
	}

}
