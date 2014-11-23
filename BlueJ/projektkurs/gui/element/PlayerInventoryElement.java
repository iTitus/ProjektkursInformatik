package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;

import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
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
     *            Höhe
     * @param id
     *            Nummer
     * @param gui
     *            Gui/Listener
     * @param inv
     *            zu repräsentierendes PlayerInventory
     */
    public PlayerInventoryElement(int posX, int posY, int sizeX, int sizeY, int id, IPlayerInventoryElementListener gui, PlayerInventory inv) {
        super(posX, posY, sizeX, sizeY, id, gui, inv);
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
     *            zu repräsentierendes PlayerInventory
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
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        PlayerInventory pInv = (PlayerInventory) inv;
        if (by > 0) {
            pInv.setSelectedItemStack(pInv.getSelectedIndex() >= pInv.getSize() ? 0 : pInv.getSelectedIndex() + 1);
        } else if (by < 0) {
            pInv.setSelectedItemStack(pInv.getSelectedIndex() <= 0 ? pInv.getSize() - 1 : pInv.getSelectedIndex() - 1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        ItemStack stack;
        for (int i = 0; i < inv.getSize(); i++) {
            RenderUtil.drawImage(g, i == ((PlayerInventory) inv).getSelectedIndex() ? Images.slothighlight : Images.slot, i * Integers.SLOT_SIZE + posX, posY,
                    Integers.SLOT_SIZE, Integers.SLOT_SIZE);
            stack = inv.getItemStackAt(i);
            if (stack != null) {
                RenderUtil.drawImage(g, stack.getImage(), i * Integers.SLOT_SIZE + posX + 1, posY + 1);
                g.drawString(stack.getStackSize() + "", i * Integers.SLOT_SIZE + posX + 1, posY + 11);
            }
        }
    }

}
