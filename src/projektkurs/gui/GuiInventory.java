package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.IInventoryElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

/**
 * Das Invetar-GUI.
 */
public class GuiInventory extends Gui implements IInventoryElementListener {

    /**
     * Das anzuzeigende Inventar.
     */
    private final Inventory inv;

    /**
     * Konstruktor.
     * @param inv
     * anzuzeigendes Inventar
     */
    public GuiInventory(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new InventoryElement(0, this, inv));
        addElement(new InventoryElement(MathUtil.floorDiv(Integers.windowX, 2), Integers.windowY - MathUtil.floorDiv(Integers.SLOT_SIZE, 2), 1, this, Main.getPlayer().getInventory()));
    }

    @Override
    public void onSlotLeftClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        if (invE.getID() == 0) {
            if (Main.getPlayer().getInventory().addItemStack(inv.getItemStackAt(slotIndex))) {
                inv.removeItemStack(slotIndex);
            }
        } else if (invE.getID() == 1) {
            if (inv.addItemStack(Main.getPlayer().getInventory().getItemStackAt(slotIndex))) {
                Main.getPlayer().getInventory().removeItemStack(slotIndex);
            }
        }
    }

    @Override
    public void onSlotRightClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        if (invE.getID() == 0) {
            ItemStack stack = inv.getItemStackAt(slotIndex);
            if (stack != null && Main.getPlayer().getInventory().addItemStack(stack.split(1))) {
                inv.decrStackSize(slotIndex, 1);
            }
        } else if (invE.getID() == 1) {
            if (inv.addItemStack(Main.getPlayer().getInventory().getItemStackAt(slotIndex).split(1))) {
                Main.getPlayer().getInventory().decrStackSize(slotIndex, 1);
            }
        }
    }

}
