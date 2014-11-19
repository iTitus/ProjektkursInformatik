package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.IInventoryElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

public class GuiInventory extends Gui implements IInventoryElementListener {

	private Inventory inv;

	public GuiInventory(Inventory inv) {
		this.inv = inv;
	}

	@Override
	public void initGui() {
		super.initGui();
		guiElements.add(new InventoryElement(0, this, inv));
		guiElements.add(new InventoryElement(MathUtil.floorDiv(
				Integers.WINDOW_X, 2), Integers.WINDOW_Y
				- MathUtil.floorDiv(Integers.SLOT_SIZE, 2), 1, this, Main
				.getPlayer().getInventory()));
	}

	@Override
	public void onLeftClick(int screenX, int screenY, MouseEvent e) {
		super.onLeftClick(screenX, screenY, e);
	}

	@Override
	public void onSlotLeftClick(int slotIndex, InventoryElement invE,
			MouseEvent e) {
		if (invE.getId() == 0) {
			if (Main.getPlayer().getInventory()
					.addItemStack(inv.getItemStackAt(slotIndex))) {
				inv.removeItemStack(slotIndex);
			}
		} else if (invE.getId() == 1) {
			if (inv.addItemStack(Main.getPlayer().getInventory()
					.getItemStackAt(slotIndex))) {
				Main.getPlayer().getInventory().removeItemStack(slotIndex);
			}
		}
	}

	@Override
	public void onSlotRightClick(int slotIndex, InventoryElement invE,
			MouseEvent e) {
		if (invE.getId() == 0) {
			ItemStack stack = inv.getItemStackAt(slotIndex);
			if (stack != null
					&& Main.getPlayer().getInventory()
							.addItemStack(stack.split(1))) {
				inv.decrStackSize(slotIndex, 1);
			}
		} else if (invE.getId() == 1) {
			if (inv.addItemStack(Main.getPlayer().getInventory()
					.getItemStackAt(slotIndex).split(1))) {
				Main.getPlayer().getInventory().decrStackSize(slotIndex, 1);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);
		super.render(g);
	}
}
