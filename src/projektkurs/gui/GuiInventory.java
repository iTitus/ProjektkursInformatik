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
	public void onLeftClick(int screenX, int screenY) {
		super.onLeftClick(screenX, screenY);
		if (screenX >= (MathUtil.roundDiv(Integers.WINDOW_X, 2) - MathUtil
				.roundDiv(Integers.SLOT_SIZE * inv.getSize(), 2))
				&& screenX < inv.getSize()
						* Integers.SLOT_SIZE
						+ (MathUtil.roundDiv(Integers.WINDOW_X, 2) - MathUtil
								.roundDiv(Integers.SLOT_SIZE * inv.getSize(), 2))
				&& screenY >= (MathUtil.roundDiv(Integers.WINDOW_Y, 2) - MathUtil
						.roundDiv(Integers.SLOT_SIZE, 2))
				&& screenY < (MathUtil.roundDiv(Integers.WINDOW_Y, 2) - MathUtil
						.roundDiv(Integers.SLOT_SIZE, 2)) + Integers.SLOT_SIZE) {
			int index = ((screenX - (MathUtil.roundDiv(Integers.WINDOW_X, 2) - MathUtil
					.roundDiv(Integers.SLOT_SIZE * inv.getSize(), 2))) / Integers.SLOT_SIZE);
			if (Main.getPlayer().getInventory()
					.addItemStack(inv.getItemStackAt(index)))
				inv.removeItemStack(index);
		} else if (screenX >= Main.getPlayer().getInventory().getRelX()
				&& screenX < Main.getPlayer().getInventory().getSize()
						* Integers.SLOT_SIZE
						+ Main.getPlayer().getInventory().getRelX()
				&& screenY >= Main.getPlayer().getInventory().getRelY()
				&& screenY < Main.getPlayer().getInventory().getRelY()
						+ Integers.SLOT_SIZE) {
			int index = ((screenX - Main.getPlayer().getInventory().getRelX()) / Integers.SLOT_SIZE);
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
									+ (MathUtil.roundDiv(Integers.WINDOW_X, 2) - MathUtil
											.roundDiv(
													Integers.SLOT_SIZE
															* inv.getSize(), 2)),
							(MathUtil.roundDiv(Integers.WINDOW_Y, 2) - MathUtil
									.roundDiv(Integers.SLOT_SIZE, 2)));
			stack = inv.getItemStackAt(i);
			if (stack != null) {
				RenderUtil
						.drawImage(
								g,
								stack.getImage(),
								i
										* Integers.SLOT_SIZE
										+ (MathUtil.roundDiv(Integers.WINDOW_X,
												2) - MathUtil.roundDiv(
												Integers.SLOT_SIZE
														* inv.getSize(), 2))
										+ 1, (MathUtil.roundDiv(
										Integers.WINDOW_Y, 2) - MathUtil
										.roundDiv(Integers.SLOT_SIZE, 2)) + 1);
				g.drawString(
						stack.getStackSize() + "",
						i
								* Integers.SLOT_SIZE
								+ (MathUtil.roundDiv(Integers.WINDOW_X, 2) - MathUtil
										.roundDiv(
												Integers.SLOT_SIZE
														* inv.getSize(), 2))
								+ 1,
						(MathUtil.roundDiv(Integers.WINDOW_Y, 2) - MathUtil
								.roundDiv(Integers.SLOT_SIZE, 2)) + 11);
			}
		}

		super.render(g);
	}
}
