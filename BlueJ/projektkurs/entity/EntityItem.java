package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;
import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

/**
 * Ein ItemStack, der auf dem Boden liegt.
 */
public class EntityItem extends Entity {

    /**
     * Der enthaltene ItemStack.
     */
    private ItemStack stack;

    /**
     * Konstruktor.
     */
    public EntityItem() {
        super();
    }

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param stack
     *            der enthaltene ItemStack
     */
    public EntityItem(int posX, int posY, ItemStack stack) {
        super(posX, posY, stack.getItem().getImage(stack));
        this.stack = stack;
    }

    @Override
    public BufferedImage getImage() {
        return stack.getItem().getImage(stack);
    }

    /**
     * Der entahltene ItemStack.
     *
     * @return ItemStack
     */
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        stack = ItemStack.load(data.getSaveData(Strings.ENTITY_ITEM));
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.ENTITY_ITEM, stack.write());
    }
}
