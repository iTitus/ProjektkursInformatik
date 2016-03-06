package projektkurs.item;

import projektkurs.render.Sprite;

/**
 * Ein einfaches Item.
 */
public class BaseItem extends Item {

    /**
     * Konstruktor.
     * @param id
     * ID
     * @param name
     * Name
     * @param sprite
     * Sprite
     */
    public BaseItem(int id, String name, Sprite sprite) {
        super(id, name, sprite);
    }

}
