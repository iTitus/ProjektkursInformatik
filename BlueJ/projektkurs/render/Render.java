package projektkurs.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

/**
 * Renderklasse
 */
public class Render {

	/**
	 * Das Spiel-Canvas
	 */
	private final GameCanvas canvas;
	private Graphics2D g;
	private BufferStrategy strategy;

	/**
	 * Konstruktor
	 *
	 * @param gameWindow
	 *            Das Spiel-JPanel
	 */
	public Render(GameCanvas canvas) {
		this.canvas = canvas;
		strategy = null;
		g = null;
	}

	public BufferStrategy getBufferStrategy() {
		return strategy;
	}

	/**
	 * Gibt das aktuelle Canvas zurück
	 *
	 * @return Canvas
	 */
	public GameCanvas getGameCanvas() {
		return canvas;
	}

	public void initBuffers() {
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
	}

	/**
	 * Updated den Bildschirm
	 */
	public void update() {
		if (strategy != null) {

			g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);
			g.setColor(Color.BLACK);

			g.drawString("FPS: " + Main.getFPS() + " - UPS: " + Main.getUPS(),
					Integers.WINDOW_HUD_X, 16);

			for (int x = 0; x < Integers.SIGHT_X; x++) {
				for (int y = 0; y < Integers.SIGHT_Y; y++) {
					int sX = x + Main.getRenderHelper().getSightX();
					int sY = y + Main.getRenderHelper().getSightY();
					if (Main.getSpielfeld().isRasterAt(sX, sY)) {
						Main.getSpielfeld().getRasterAt(sX, sY)
								.render(g, sX, sY);
					} else {
						RenderUtil.drawDefaultRaster(g, Images.baum, sX, sY);
					}
				}
			}

			for (Entity e : Main.getSpielfeld().getEntityList()) {
				if (!e.shouldDeSpawn()
						&& e.isInside(Main.getRenderHelper().getSightX(), Main
								.getRenderHelper().getSightY(),
								Integers.SIGHT_X, Integers.SIGHT_Y))
					e.render(g);
			}

			PlayerInventory inv = Main.getPlayer().getInventory();
			ItemStack stack = null;
			for (int i1 = 0; i1 < inv.getSize(); i1++) {
				RenderUtil.drawImage(g,
						(i1 == inv.getSelectedIndex() ? Images.slot_highlight
								: Images.slot),
						i1 * Integers.SLOT_SIZE + inv.getRelX(), inv.getRelY());
				stack = inv.getItemStackAt(i1);
				if (stack != null) {
					RenderUtil.drawImage(g, stack.getImage(), i1
							* Integers.SLOT_SIZE + inv.getRelX() + 1,
							inv.getRelY() + 1);
					g.drawString(stack.getStackSize() + "", i1
							* Integers.SLOT_SIZE + inv.getRelX() + 1,
							inv.getRelY() + 11);
				}
			}

			g.dispose();
			strategy.show();

		}
	}
}
