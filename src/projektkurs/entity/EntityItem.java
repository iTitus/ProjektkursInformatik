package projektkurs.entity;

import java.util.List;

import projektkurs.io.storage.SaveData;
import projektkurs.item.ItemStack;
import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.world.Spielfeld;

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
     *
     * @param map
     *            Spielfeld
     */
    public EntityItem(Spielfeld map) {
        super(map);
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
    public EntityItem(Spielfeld map, int posX, int posY, ItemStack stack) {
        super(map, posX, posY, stack.getItem().getSprite(stack));
        this.stack = stack;
    }

    @Override
    public Sprite getSprite() {
        return stack.getItem().getSprite(stack);
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

    @Override
    public void addTooltip(Spielfeld map, int x, int y, List<String> tooltip) {
        tooltip.add(getName() + " - " + stack.getName());
    }

}
