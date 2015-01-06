package projektkurs.item;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;
import projektkurs.util.I18n;

/**
 * Ein abstraktes Item.
 */
public abstract class Item {

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
     */
    public Item(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Das Bild.
     *
     * @return Bild
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Das Bild des gegebenen ItemStacks.
     *
     * @param stack
     *            ItemStack
     * @return Bild
     */
    public BufferedImage getImage(ItemStack stack) {
        return getImage();
    }

    /**
     * Interner Name.
     *
     * @return Name
     */
    public String getInternalName() {
        return name;
    }

    /**
     * Übersetzter Name.
     *
     * @return Name
     */
    public String getName() {
        return I18n.getString("item." + name + ".name");
    }

    /**
     * Übersetzter Name - ItemStack sensitiv.
     *
     * @param stack
     *            ItemStack
     * @return Name
     */
    public String getName(ItemStack stack) {
        return getName();
    }

    /**
     * Wird ausgeführt, wenn mit der linken Maustaster mit diesem Item auf das Fenster geklickt wird.
     *
     * @param e
     *            Entity der klickt
     * @param stack
     *            ItemStack, mit dem geklickt wird
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param event
     *            MouseEvent
     */
    public void onLeftClick(Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        // NO-OP
    }

    /**
     * Wird ausgeführt, wenn mit der rechten Maustaster mit diesem Item auf das Fenster geklickt wird.
     *
     * @param e
     *            Entity der klickt
     * @param stack
     *            ItemStack, mit dem geklickt wird
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param event
     *            MouseEvent
     */
    public void onRightClick(Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        // NO-OP
    }

    @Override
    public String toString() {
        return "Item[" + getInternalName() + "]";
    }
}