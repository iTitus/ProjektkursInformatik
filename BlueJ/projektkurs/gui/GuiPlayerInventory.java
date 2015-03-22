package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.CombinationSlotElement;
import projektkurs.gui.element.IInventoryElementListener;
import projektkurs.gui.element.ISlotElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.gui.element.SlotElement;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiPlayerInventory extends Gui implements IInventoryElementListener, ISlotElementListener {

    private Inventory combinationInputInventory;
    private CombinationSlotElement combinationSlotElement;
    private SlotElement input1, input2;

    @Override
    public void initGui() {
        super.initGui();
        addElement(new InventoryElement(0, this, Main.getPlayer().getInventory()));
        combinationInputInventory = new Inventory(2);
        input1 = new SlotElement(64, 64, 1, this, 0, combinationInputInventory);
        input2 = new SlotElement(128, 64, 2, this, 1, combinationInputInventory);
        combinationSlotElement = new CombinationSlotElement(256, 64, 3, this, input1, input2);
        addElement(input1);
        addElement(input2);
        addElement(combinationSlotElement);
    }

    @Override
    public void onSlotLeftClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        if (combinationInputInventory.addItemStack(Main.getPlayer().getInventory().getItemStackAt(slotIndex))) {
            Main.getPlayer().getInventory().removeItemStack(slotIndex);
            combinationSlotElement.onInputSlotChanged();
        }
    }

    @Override
    public void onSlotLeftClick(int slotIndex, SlotElement slotE, MouseEvent e) {
        if (slotE.getID() < 3) {
            if (Main.getPlayer().getInventory().addItemStack(slotE.getItemStack())) {
                combinationInputInventory.removeItemStack(slotIndex);
                combinationSlotElement.onInputSlotChanged();
            }
        } else {
            if (Main.getPlayer().getInventory().addItemStack(slotE.getItemStack())) {
                combinationSlotElement.onCrafted();
            }
        }
    }

    @Override
    public void onSlotRightClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        ItemStack stack = Main.getPlayer().getInventory().getItemStackAt(slotIndex);
        if (stack != null && combinationInputInventory.addItemStack(stack.split(1))) {
            Main.getPlayer().getInventory().decrStackSize(slotIndex, 1);
            combinationSlotElement.onInputSlotChanged();
        }
    }

    @Override
    public void onSlotRightClick(int slotIndex, SlotElement slotE, MouseEvent e) {
        if (slotE.getID() < 3) {
            ItemStack stack = slotE.getItemStack();
            if (stack != null && Main.getPlayer().getInventory().addItemStack(stack.split(1))) {
                combinationInputInventory.decrStackSize(slotIndex, 1);
                combinationSlotElement.onInputSlotChanged();
            }
        }
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}
