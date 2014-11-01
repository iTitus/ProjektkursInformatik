package projektkurs.render;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.render.entity.RenderEntity;
import projektkurs.util.MathUtil;

/**
 * Renderklasse
 * 
 */
public class Render {

	private int fps;
	private int staticFPS;
	private long lastFPSMeasure;

	/**
	 * Das Spiel-Canvas
	 */
	private final GameCanvas canvas;
	private BufferStrategy strategy;

	/**
	 * Konstruktor
	 * 
	 * @param gameWindow
	 *            Das Spiel-JPanel
	 */
	public Render(GameCanvas canvas) {
		this.canvas = canvas;

		lastFPSMeasure = System.nanoTime();

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
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);

			g.drawString("FPS: " + staticFPS + " - UPS: "
					+ Main.getSpielfeld().getUPS(), Integers.WINDOW_HUD_X, 16);

			for (int x = 0; x < Main.getRenderHelper().getSight().length; x++) {
				for (int y = 0; y < Main.getRenderHelper().getSight()[x].length; y++) {
					g.drawImage(Main.getRenderHelper().getSight()[x][y], x
							* Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, y
							* Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y,
							null);
				}
			}

			synchronized (Main.getRenderHelper().getEntitiesInSight()) {
				Iterator<RenderEntity> i = Main.getRenderHelper()
						.getEntitiesInSight().iterator();
				RenderEntity e;
				while (i.hasNext()) {
					e = i.next();
					g.drawImage(e.getEntity().getImage(), e.getRelX(),
							e.getRelY(), null);
				}
			}

			g.drawImage(Images.charakter, MathUtil.ceilDiv(Integers.WINDOW_X
					+ Integers.RASTER_SIZE, 2), MathUtil.ceilDiv(
					Integers.WINDOW_Y + Integers.RASTER_SIZE, 2), null);

			g.dispose();
			strategy.show();

		}
		calcFPS();
	}

	private void calcFPS() {
		if (System.nanoTime() - lastFPSMeasure > 1000000000) {
			staticFPS = fps;
			fps = 0;
			lastFPSMeasure += 1000000000;
		}
		fps++;
	}
}
