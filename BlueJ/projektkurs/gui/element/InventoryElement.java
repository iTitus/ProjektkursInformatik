package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class InventoryElement extends Element {

	protected IInventoryElementListener gui;
	protected Inventory inv;

	public InventoryElement(int id, IInventoryElementListener gui, Inventory inv) {
		this(MathUtil.floorDiv(Integers.WINDOW_X, 2), MathUtil.floorDiv(
				Integers.WINDOW_Y, 2), id, gui, inv);
	}

	public InventoryElement(int centerX, int centerY, int id,
			IInventoryElementListener gui, Inventory inv) {
		this(
				centerX
						- MathUtil.floorDiv(Integers.SLOT_SIZE * inv.getSize(),
								2), centerY
						- MathUtil.floorDiv(Integers.SLOT_SIZE, 2),
				Integers.SLOT_SIZE * inv.getSize(), Integers.SLOT_SIZE, id,
				gui, inv);
	}

	public InventoryElement(int posX, int posY, int sizeX, int sizeY, int id,
			IInventoryElementListener gui, Inventory inv) {
		super(posX, posY, sizeX, sizeY, id);
		this.gui = gui;
		this.inv = inv;
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			gui.onSlotLeftClick(
					MathUtil.floorDiv((x - posX), Integers.SLOT_SIZE), this, e);
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			gui.onSlotRightClick(
					MathUtil.floorDiv((x - posX), Integers.SLOT_SIZE), this, e);
		}
	}

	@Override
	public void render(Graphics2D g) {
		ItemStack stack;
		for (int i = 0; i < inv.getSize(); i++) {
			RenderUtil.drawImage(g, Images.slot, i * Integers.SLOT_SIZE + posX,
					posY, Integers.SLOT_SIZE, Integers.SLOT_SIZE);
			stack = inv.getItemStackAt(i);
			if (stack != null) {
				RenderUtil.drawImage(g, stack.getImage(), i
						* Integers.SLOT_SIZE + posX + 1, posY + 1);
				g.drawString(stack.getStackSize() + "", i * Integers.SLOT_SIZE
						+ posX + 1, posY + 11);
			}
		}
	}

}
