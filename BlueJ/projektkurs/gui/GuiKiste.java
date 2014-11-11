package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.world.raster.extra.ExtraInformationKiste;

public class GuiKiste extends Gui {

	private ExtraInformationKiste kiste;

	public GuiKiste(ExtraInformationKiste kiste) {
		this.kiste = kiste;
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);
		g.setColor(Color.BLUE);
		ItemStack stack;
		for (int i = 0; i < kiste.getInventar().getSize(); i++) {
			RenderUtil.drawImage(g, Images.slot, i * Integers.SLOT_SIZE
					+ MathUtil.ceilDiv(Integers.WINDOW_X, 2),
					MathUtil.ceilDiv(Integers.WINDOW_Y, 2));
			stack = kiste.getInventar().getItemStackAt(i);
			if (stack != null) {
				RenderUtil.drawImage(
						g,
						stack.getImage(),
						i * Integers.SLOT_SIZE
								+ MathUtil.ceilDiv(Integers.WINDOW_X, 2) + 1,
						MathUtil.ceilDiv(Integers.WINDOW_Y, 2) + 1);
				g.drawString(stack.getStackSize() + "", i * Integers.SLOT_SIZE
						+ MathUtil.ceilDiv(Integers.WINDOW_X, 2) + 1,
						MathUtil.ceilDiv(Integers.WINDOW_Y, 2) + 11);
			}
		}
	}

	@Override
	public void onLeftClick(int x, int y) {
		if (x >= MathUtil.ceilDiv(Integers.WINDOW_X, 2)
				&& x < kiste.getInventar().getSize() * Integers.SLOT_SIZE
						+ MathUtil.ceilDiv(Integers.WINDOW_X, 2)
				&& y >= MathUtil.ceilDiv(Integers.WINDOW_Y, 2)
				&& y < MathUtil.ceilDiv(Integers.WINDOW_Y, 2)
						+ Integers.SLOT_SIZE) {
			int index = ((x - MathUtil.ceilDiv(Integers.WINDOW_X, 2)) / Integers.SLOT_SIZE);
			if (Main.getPlayer().getInventory()
					.addItemStack(kiste.getInventar().getItemStackAt(index)))
				kiste.getInventar().removeItemStack(index);
		} else if (x >= Main.getPlayer().getInventory().getRelX()
				&& x < Main.getPlayer().getInventory().getSize()
						* Integers.SLOT_SIZE
						+ Main.getPlayer().getInventory().getRelX()
				&& y >= Main.getPlayer().getInventory().getRelY()
				&& y < Main.getPlayer().getInventory().getRelY()
						+ Integers.SLOT_SIZE) {
			int index = ((x - Main.getPlayer().getInventory().getRelX()) / Integers.SLOT_SIZE);
			if (kiste.getInventar().addItemStack(
					Main.getPlayer().getInventory().getItemStackAt(index)))
				Main.getPlayer().getInventory().removeItemStack(index);
		}
	}
}
