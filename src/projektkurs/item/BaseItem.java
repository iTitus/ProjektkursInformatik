package projektkurs.item;

import java.awt.image.BufferedImage;

import projektkurs.util.I18n;

/**
 * Ein einfaches Item.
 */
public class BaseItem extends AbstractItem {

    /**
     * Bild dieses Items.
     */
    private final BufferedImage image;
    /**
     * Name dieses Items.
     */
    private final String name;

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param image
     *            Bild
     */
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
