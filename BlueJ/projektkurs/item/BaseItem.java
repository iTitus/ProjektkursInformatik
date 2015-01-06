package projektkurs.item;

import java.awt.image.BufferedImage;

/**
 * Ein einfaches Item.
 */
public class BaseItem extends Item {

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param image
     *            Bild
     */
    public BaseItem(String name, BufferedImage image) {
        super(name, image);
    }

}
