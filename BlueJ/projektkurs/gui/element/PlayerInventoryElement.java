package projektkurs.gui.element;

import java.awt.event.MouseWheelEvent;

import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class PlayerInventoryElement extends InventoryElement {

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     * @param id
     *            Nummer
     * @param listener
     *            Listener
     * @param inv
     *            zu repraesentierendes PlayerInventory
     */
    public PlayerInventoryElement(int posX, int posY, int sizeX, int sizeY, int id, IPlayerInventoryElementListener listener, PlayerInventory inv) {
        super(posX, posY, sizeX, sizeY, id, listener, inv);
    }

    /**
     * Konstruktor.
     *
     * @param centerX
     *            X-Koordinate des Mittelpunkts
     * @param centerY
     *            Y-Koordinate des Mittelpunkts
     * @param id
     *            Nummer
     * @param gui
     *            Gui/Listener
     * @param inv
     *            zu repraesentierendes PlayerInventory
     */
    public PlayerInventoryElement(int centerX, int centerY, int id, IPlayerInventoryElementListener gui, PlayerInventory inv) {
        super(centerX, centerY, id, gui, inv);
    }

    /**
     * Das PlayerInventory.
     *
     * @return PlayerInventory
     */
    @Override
    public PlayerInventory getInventory() {
        return (PlayerInventory) inv;
    }

    @Override
    public IPlayerInventoryElementListener getListener() {
        return (IPlayerInventoryElementListener) super.getListener();
    }

    @Override
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        PlayerInventory pInv = (PlayerInventory) inv;
        if (by > 0) {
            pInv.setSelectedItemStack(pInv.getSelectedIndex() >= pInv.getSize() ? 0 : pInv.getSelectedIndex() + 1);
            getListener().onSlotChanged(this, e);
        } else if (by < 0) {
            pInv.setSelectedItemStack(pInv.getSelectedIndex() <= 0 ? pInv.getSize() - 1 : pInv.getSelectedIndex() - 1);
            getListener().onSlotChanged(this, e);
        }
    }

    @Override
    public void render(Screen screen) {
        ItemStack stack;
        for (int i = 0; i < inv.getSize(); i++) {
            RenderUtil.drawSprite(screen, i == ((PlayerInventory) inv).getSelectedIndex() ? Sprites.slot_highlight : Sprites.slot, i * Integers.SLOT_SIZE + posX, posY);
            stack = inv.getItemStackAt(i);
            if (stack != null) {
                RenderUtil.drawSprite(screen, stack.getSprite(), i * Integers.SLOT_SIZE + posX + 1, posY + 1);
                Font.drawString(screen, stack.getStackSize() + "", i * Integers.SLOT_SIZE + posX + 1, posY + 11);
            }
        }
    }

}
