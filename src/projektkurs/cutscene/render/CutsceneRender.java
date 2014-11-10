package projektkurs.cutscene.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

/**
 * 
 */
public class CutsceneRender {

	public static class CutSceneCanvas extends Canvas {

		private static final long serialVersionUID = 1L;

		public CutSceneCanvas() {
			requestFocus();

			setIgnoreRepaint(true);
			setBounds(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);
		}

	}

	private final CutSceneCanvas canvas;
	private Graphics2D g;

	private BufferStrategy strategy;

	public CutsceneRender() {
		canvas = new CutSceneCanvas();
	}

	public CutSceneCanvas getCutSceneCanvas() {
		return canvas;
	}

	public void initBuffers() {
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
	}

	public void update() {
		if (strategy != null) {
			g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);

			g.setColor(Color.BLACK);
			g.drawString("FPS: " + CutSceneManager.getFPS() + " - UPS: "
					+ CutSceneManager.getUPS(), 16, 16);

			if (CutSceneManager.getCurrentCutScene().needsRasterBackground()) {
				for (int x = 0; x < Integers.SIGHT_X; x++) {
					for (int y = 0; y < Integers.SIGHT_Y; y++) {
						int rX = x
								+ CutSceneManager
										.getCurrentCutSceneRenderHelper()
										.getSightX();
						int rY = y
								+ CutSceneManager
										.getCurrentCutSceneRenderHelper()
										.getSightY();
						if (CutSceneManager.getCurrSpielfeld().isRasterAt(rX,
								rY)) {
							CutSceneManager.getCurrSpielfeld()
									.getRasterAt(rX, rY)
									.renderCutScene(g, rX, rY);
						} else {
							RenderUtil
									.drawDefaultRaster(g, Images.baum, rX, rY);
						}
					}
				}
			} else {
				RenderUtil.drawImage(g, CutSceneManager.getCurrentCutScene()
						.getBackground(), Integers.WINDOW_HUD_X,
						Integers.WINDOW_HUD_Y, Integers.SIGHT_X,
						Integers.SIGHT_Y);
			}

			for (CutSceneObject obj : CutSceneManager.getCurrentCutScene()
					.getCutSceneObjectList()) {
				if (obj.isInside(CutSceneManager
						.getCurrentCutSceneRenderHelper().getSightX(),
						CutSceneManager.getCurrentCutSceneRenderHelper()
								.getSightY(), Integers.SIGHT_X,
						Integers.SIGHT_Y)) {
					obj.render(g);
				}
			}

			g.dispose();
			strategy.show();
		}
	}

}
