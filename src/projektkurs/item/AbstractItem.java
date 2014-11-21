package projektkurs.item;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;

/**
 * Ein abstraktes Item.
 */
public abstract class AbstractItem {

    /**
     * Das Bild.
     *
     * @return Bild
     */
    public abstract BufferedImage getImage();

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
     * Übersetzter Name.
     *
     * @return Name
     */
    public abstract String getName();

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
        return getName();
    }
}
