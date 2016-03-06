package projektkurs.item;

import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.gui.Gui;
import projektkurs.render.Sprite;
import projektkurs.util.I18n;
import projektkurs.world.Spielfeld;

/**
 * Ein abstraktes Item.
 */
public abstract class Item {

    /**
     * ID dieses Items.
     */
    private final int id;
    /**
     * Bild dieses Items.
     */
    private final Sprite image;
    /**
     * Name dieses Items.
     */
    private final String name;

    /**
     * Konstruktor.
     * @param id
     * ID
     * @param name
     * Name
     * @param sprite
     * Sprite
     */
    public Item(int id, String name, Sprite sprite) {
        this.id = id;
        this.name = name;
        image = sprite;
    }

    public void addTooltip(Gui gui, int mouseX, int mouseY, ItemStack stack, List<String> tooltip) {
        tooltip.add(getName());
        if (Main.getInputManager().isShiftDown()) {
            tooltip.add(I18n.getStringFormatted("tooltip.item.damage", stack.getDamage()));
        }
    }

    /**
     * ID.
     * @return ID
     */
    public int getID() {
        return id;
    }

    /**
     * Interner Name.
     * @return Name
     */
    public String getInternalName() {
        return name;
    }

    /**
     * Uebersetzter Name.
     * @return Name
     */
    public String getName() {
        return I18n.getString("item." + name + ".name");
    }

    /**
     * Uebersetzter Name - ItemStack sensitiv.
     * @param stack
     * ItemStack
     * @return Name
     */
    public String getName(ItemStack stack) {
        return getName();
    }

    /**
     * Das Bild.
     * @return Bild
     */
    public Sprite getSprite() {
        return image;
    }

    /**
     * Das Bild des gegebenen ItemStacks.
     * @param stack
     * ItemStack
     * @return Bild
     */
    public Sprite getSprite(ItemStack stack) {
        return getSprite();
    }

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaster mit diesem Item auf das Fenster geklickt wird.
     * @param map
     * Spielfeld
     * @param e
     * Entity der klickt
     * @param stack
     * ItemStack, mit dem geklickt wird
     * @param screenX
     * X-Bildschirmkoordinate
     * @param screenY
     * Y-Bildschirmkoordinate
     * @param event
     * MouseEvent
     */
    public void onLeftClick(Spielfeld map, Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        // NO-OP
    }

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaster mit diesem Item auf das Fenster geklickt wird.
     * @param map
     * Spielfeld
     * @param e
     * Entity der klickt
     * @param stack
     * ItemStack, mit dem geklickt wird
     * @param screenX
     * X-Bildschirmkoordinate
     * @param screenY
     * Y-Bildschirmkoordinate
     * @param event
     * MouseEvent
     */
    public void onRightClick(Spielfeld map, Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        // NO-OP
    }

    @Override
    public String toString() {
        return "Item[" + getID() + ", " + getInternalName() + "]";
    }
}
