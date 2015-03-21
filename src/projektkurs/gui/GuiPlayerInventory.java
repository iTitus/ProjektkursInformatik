package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.gui.element.CombinationElement;
import projektkurs.gui.element.IInventoryElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.inventory.PlayerInventory;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiPlayerInventory extends Gui implements IInventoryElementListener {

    @SuppressWarnings("unused")
    private CombinationElement combinationElement;
    private final PlayerInventory inventory;

    public GuiPlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new InventoryElement(0, this, inventory));
        combinationElement = new CombinationElement(64, 64, 128, 16, 1, this);
    }

    @Override
    public void onSlotLeftClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        // TODO
    }

    @Override
    public void onSlotRightClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        // TODO
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}
