package projektkurs.gui.element;

import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class SlotElement extends Element {

    protected final Inventory inventory;
    protected final int slotIndex;

    public SlotElement(int posX, int posY, int id, ISlotElementListener listener, int slotIndex, Inventory inventory) {
        super(posX, posY, Integers.SLOT_SIZE, Integers.SLOT_SIZE, id, listener);
        this.slotIndex = slotIndex;
        this.inventory = inventory;
    }

    @Override
    public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
        ItemStack hovered = getItemStack();
        if (hovered != null) {
            hovered.addTooltip(gui, mouseX, mouseY, tooltip);
        }
    }

    public void decrStackSize(int by) {
        inventory.decrStackSize(slotIndex, by);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack getItemStack() {
        return inventory.getItemStackAt(slotIndex);
    }

    @Override
    public ISlotElementListener getListener() {
        return (ISlotElementListener) super.getListener();
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void incrStackSize(int by) {
        inventory.incrStackSize(slotIndex, by);
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            getListener().onSlotLeftClick(slotIndex, this, e);
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            getListener().onSlotRightClick(slotIndex, this, e);
        }
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawSprite(screen, Sprites.slot, posX, posY);
        ItemStack stack = inventory.getItemStackAt(slotIndex);
        if (stack != null) {
            RenderUtil.drawSprite(screen, stack.getSprite(), posX + 1, posY + 1);
            Font.drawString(screen, stack.getStackSize() + "", posX + 1, posY + 11);
        }
    }

}
