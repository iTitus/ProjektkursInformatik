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

    /**
     * Das Canvas, auf dem die CutScene gemalt wird.
     */
    private Canvas         canvas;
    /**
     * Das Graphics2D Objekt der aktuellen CutScene.
     */
    private Graphics2D     g;
    /**
     * Die BufferStrategy der aktuellen CutScene.
     */
    private BufferStrategy strategy;

    /**
     * Konstruktor.
     */
    public CutsceneRender() {
        canvas = null;
        g = null;
        strategy = null;
    }

    /**
     * Das Canvas der aktuellen CutScene.
     *
     * @return Canvas der aktuellen CutScene
     */
    public Canvas getCutSceneCanvas() {
        if (canvas == null) {
            canvas = new Canvas();
            canvas.requestFocus();
            canvas.setIgnoreRepaint(true);
            canvas.setBounds(0, 0, Integers.windowX, Integers.windowY);
        }
        return canvas;
    }

    /**
     * Initialisiert die BufferStrategy der aktuellen CutScene.
     */
    public void initBuffers() {
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
    }

    /**
     * Rendert die aktuelle CutScene.
     */
    public void update() {
        if (strategy != null) {
            g = (Graphics2D) strategy.getDrawGraphics();

            g.clearRect(0, 0, Integers.windowX, Integers.windowY);

            g.setColor(Color.BLACK);
            g.drawString("FPS: " + CutSceneManager.getFPS() + " - UPS: " + CutSceneManager.getUPS(), Integers.INFO_X, Integers.INFO_Y);

            if (CutSceneManager.getCurrentCutScene().needsRasterBackground()) {
                for (int x = 0; x < Integers.sightX; x++) {
                    for (int y = 0; y < Integers.sightY; y++) {
                        int rX = x + CutSceneManager.getCurrentCutSceneRenderHelper().getSightX();
                        int rY = y + CutSceneManager.getCurrentCutSceneRenderHelper().getSightY();
                        if (CutSceneManager.getCurrSpielfeld().isRasterAt(rX, rY)) {
                            CutSceneManager.getCurrSpielfeld().getRasterAt(rX, rY).renderCutScene(g, rX, rY);
                        } else {
                            RenderUtil.drawDefaultRaster(g, Images.baum, rX, rY);
                        }
                    }
                }
            } else {
                RenderUtil.drawImage(g, CutSceneManager.getCurrentCutScene().getBackground(), Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.sightX,
                        Integers.sightY);
            }

            for (CutSceneObject obj : CutSceneManager.getCurrentCutScene().getCutSceneObjectList()) {
                if (obj.isInside(CutSceneManager.getCurrentCutSceneRenderHelper().getSightX(), CutSceneManager.getCurrentCutSceneRenderHelper().getSightY(),
                        Integers.sightX, Integers.sightY)) {
                    obj.render(g);
                }
            }

            g.dispose();
            strategy.show();
        }
    }

}
