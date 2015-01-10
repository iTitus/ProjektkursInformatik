package projektkurs.cutscene.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.IUpdatable;
import projektkurs.util.RenderUtil;

/**
 * CutScene-Renderklasse.
 */
public class CutsceneRender implements IUpdatable {

    /**
     * Das Canvas, auf dem die CutScene gemalt wird.
     */
    private Canvas canvas;
    /**
     * Das Graphics2D Objekt der aktuellen CutScene.
     */
    private Graphics2D g;
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

    @Override
    public boolean canUpdate() {
        return true;
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
        canvas.createBufferStrategy(3);
        strategy = canvas.getBufferStrategy();
    }

    /**
     * Rendert die aktuelle CutScene.
     */
    @Override
    public void update() {
        if (strategy != null) {
            g = (Graphics2D) strategy.getDrawGraphics();

            g.clearRect(0, 0, Integers.windowX, Integers.windowY);

            if (CutSceneManager.getCutScene().needsRasterBackground()) {
                for (int y = CutSceneManager.getCutSceneRenderHelper().getSightY(); y < CutSceneManager.getCutSceneRenderHelper().getSightY() + Integers.sightY; y++) {
                    for (int x = CutSceneManager.getCutSceneRenderHelper().getSightX(); x < CutSceneManager.getCutSceneRenderHelper().getSightX() + Integers.sightX; x++) {
                        if (CutSceneManager.getMap().isRasterAt(x, y)) {
                            CutSceneManager.getMap().getRasterAt(x, y).renderCutScene(g, x, y);
                        } else {
                            RenderUtil.drawDefaultRaster(g, Images.baum, x, y);
                        }
                    }
                }
            } else {
                RenderUtil.drawImage(g, CutSceneManager.getCutScene().getBackground(), Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.windowY - 2 * Integers.WINDOW_HUD_Y);
            }

            for (CutSceneObject obj : CutSceneManager.getCutScene().getCutSceneObjectList()) {
                if (obj.isInside(CutSceneManager.getCutSceneRenderHelper().getSightX(), CutSceneManager.getCutSceneRenderHelper().getSightY(), Integers.sightX, Integers.sightY)) {
                    obj.render(g);
                }
            }

            RenderUtil.drawBorder(g);

            g.setColor(Color.BLACK);
            g.drawString("FPS: " + CutSceneManager.getFPS() + " - UPS: " + CutSceneManager.getUPS(), Integers.INFO_X, Integers.INFO_Y);

            g.dispose();
            strategy.show();
        }
    }

}
