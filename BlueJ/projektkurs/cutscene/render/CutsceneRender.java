package projektkurs.cutscene.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

//FIXME: MUST BE USED!
public class CutsceneRender {

	private Graphics2D g;
	private final CutSceneCanvas canvas;
	private BufferStrategy strategy;

	public static class CutSceneCanvas extends Canvas {

		private static final long serialVersionUID = 1L;

		public CutSceneCanvas() {
			// addKeyListener(Main.getInputManager());
			// addMouseListener(Main.getInputManager());
			// addMouseMotionListener(Main.getInputManager());
			// addMouseWheelListener(Main.getInputManager());

			requestFocus();

			setIgnoreRepaint(true);
			setBounds(0, 0, Integers.SIGHT_X * Integers.RASTER_SIZE,
					Integers.SIGHT_Y * Integers.RASTER_SIZE);
		}

	}

	public CutsceneRender() {
		canvas = new CutSceneCanvas();
	}

	public void update() {
		if (strategy != null) {
			g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, CutSceneManager.getCurrentCutScene()
					.getCutSceneMap().getMapSizeX()
					* Integers.RASTER_SIZE, CutSceneManager
					.getCurrentCutScene().getCutSceneMap().getMapSizeY()
					* Integers.RASTER_SIZE);

			g.setColor(Color.BLACK);
			g.drawString("FPS: " + CutSceneManager.getFPS() + " - UPS: "
					+ CutSceneManager.getUPS(), 16, 16);

			for (int x = 0; x < CutSceneManager.getCurrentCutScene()
					.getCutSceneMap().getMapSizeX(); x++) {
				for (int y = 0; y < CutSceneManager.getCurrentCutScene()
						.getCutSceneMap().getMapSizeY(); y++) {
					int sX = x
							+ CutSceneManager.getCurrentCutSceneRenderHelper()
									.getSightX();
					int sY = y
							+ CutSceneManager.getCurrentCutSceneRenderHelper()
									.getSightY();
					RenderUtil.drawDefaultRaster(g,
							CutSceneManager.getCurrentCutScene()
									.getCutSceneMap().getImageAt(sX, sY), sX,
							sY);
				}
			}

			for (CutSceneObject obj : CutSceneManager.getCurrentCutScene()
					.getCutSceneMap().getCutSceneObjectList()) {
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

	public CutSceneCanvas getCutSceneCanvas() {
		return canvas;
	}

	public void initBuffers() {
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
	}

}
