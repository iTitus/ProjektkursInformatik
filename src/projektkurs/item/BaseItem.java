package projektkurs.item;

import java.awt.image.BufferedImage;

/**
 * Ein einfaches Item.
 */
public class BaseItem extends Item {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     * @param name
     *            Name
     * @param image
     *            Bild
     */
    public BaseItem(int id, String name, BufferedImage image) {
        super(id, name, image);
    }

}
