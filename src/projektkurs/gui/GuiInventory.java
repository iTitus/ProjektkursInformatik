package projektkurs.gui;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class GuiInventory extends Gui {

	private Inventory inv;

	public GuiInventory(Inventory inv) {
		this.inv = inv;
	}

	@Override
	public void onLeftClick(int x, int y) {
		if (x >= (MathUtil.ceilDiv(Integers.WINDOW_X, 2) - MathUtil.ceilDiv(
				Integers.SLOT_SIZE * inv.getSize(), 2))
				&& x < inv.getSize()
						* Integers.SLOT_SIZE
						+ (MathUtil.ceilDiv(Integers.WINDOW_X, 2) - MathUtil
								.ceilDiv(Integers.SLOT_SIZE * inv.getSize(), 2))
				&& y >= (MathUtil.ceilDiv(Integers.WINDOW_Y, 2) - MathUtil
						.ceilDiv(Integers.SLOT_SIZE, 2))
				&& y < (MathUtil.ceilDiv(Integers.WINDOW_Y, 2) - MathUtil
						.ceilDiv(Integers.SLOT_SIZE, 2)) + Integers.SLOT_SIZE) {
			int index = ((x - (MathUtil.ceilDiv(Integers.WINDOW_X, 2) - MathUtil
					.ceilDiv(Integers.SLOT_SIZE * inv.getSize(), 2))) / Integers.SLOT_SIZE);
			if (Main.getPlayer().getInventory()
					.addItemStack(inv.getItemStackAt(index)))
				inv.removeItemStack(index);
		} else if (x >= Main.getPlayer().getInventory().getRelX()
				&& x < Main.getPlayer().getInventory().getSize()
						* Integers.SLOT_SIZE
						+ Main.getPlayer().getInventory().getRelX()
				&& y >= Main.getPlayer().getInventory().getRelY()
				&& y < Main.getPlayer().getInventory().getRelY()
						+ Integers.SLOT_SIZE) {
			int index = ((x - Main.getPlayer().getInventory().getRelX()) / Integers.SLOT_SIZE);
			if (inv.addItemStack(Main.getPlayer().getInventory()
					.getItemStackAt(index)))
				Main.getPlayer().getInventory().removeItemStack(index);
		}
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);

		ItemStack stack;
		for (int i = 0; i < inv.getSize(); i++) {
			RenderUtil
					.drawImage(
							g,
							Images.slot,
							i
									* Integers.SLOT_SIZE
									+ (MathUtil.ceilDiv(Integers.WINDOW_X, 2) - MathUtil
											.ceilDiv(
													Integers.SLOT_SIZE
															* inv.getSize(), 2)),
							(MathUtil.ceilDiv(Integers.WINDOW_Y, 2) - MathUtil
									.ceilDiv(Integers.SLOT_SIZE, 2)));
			stack = inv.getItemStackAt(i);
			if (stack != null) {
				RenderUtil
						.drawImage(
								g,
								stack.getImage(),
								i
										* Integers.SLOT_SIZE
										+ (MathUtil.ceilDiv(Integers.WINDOW_X,
												2) - MathUtil.ceilDiv(
												Integers.SLOT_SIZE
														* inv.getSize(), 2))
										+ 1, (MathUtil.ceilDiv(
										Integers.WINDOW_Y, 2) - MathUtil
										.ceilDiv(Integers.SLOT_SIZE, 2)) + 1);
				g.drawString(
						stack.getStackSize() + "",
						i
								* Integers.SLOT_SIZE
								+ (MathUtil.ceilDiv(Integers.WINDOW_X, 2) - MathUtil
										.ceilDiv(
												Integers.SLOT_SIZE
														* inv.getSize(), 2))
								+ 1,
						(MathUtil.ceilDiv(Integers.WINDOW_Y, 2) - MathUtil
								.ceilDiv(Integers.SLOT_SIZE, 2)) + 11);
			}
		}
	}
}
