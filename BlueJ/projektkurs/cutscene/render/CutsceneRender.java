package projektkurs.cutscene.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Screen;
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
     * Bild.
     */
    private final BufferedImage image = new BufferedImage(Integers.windowX, Integers.windowY, BufferedImage.TYPE_INT_RGB);
    /**
     * Die zum Bild gehoerigen Pixel.
     */
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    /**
     * Screen.
     */
    private final Screen screen;
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
        screen = new Screen(Integers.windowX, Integers.windowY);
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

            // g.clearRect(0, 0, Integers.windowX, Integers.windowY);

            if (CutSceneManager.getCutScene().needsRasterBackground()) {
                for (int y = CutSceneManager.getCutSceneRenderHelper().getSightY(); y < CutSceneManager.getCutSceneRenderHelper().getSightY() + Integers.sightY; y++) {
                    for (int x = CutSceneManager.getCutSceneRenderHelper().getSightX(); x < CutSceneManager.getCutSceneRenderHelper().getSightX() + Integers.sightX; x++) {
                        if (CutSceneManager.getMap().isRasterAt(x, y)) {
                            CutSceneManager.getMap().getRasterAt(x, y).renderCutScene(screen, x, y);
                        } else {
                            RenderUtil.drawDefaultRaster(screen, Sprites.tree, x, y);
                        }
                    }
                }
            } else {
                RenderUtil.drawSprite(screen, CutSceneManager.getCutScene().getBackground(), Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y);
            }

            for (CutSceneObject obj : CutSceneManager.getCutScene().getCutSceneObjectList()) {
                if (obj.isInside(CutSceneManager.getCutSceneRenderHelper().getSightX(), CutSceneManager.getCutSceneRenderHelper().getSightY(), Integers.sightX, Integers.sightY)) {
                    obj.render(screen);
                }
            }

            RenderUtil.drawBorder(screen);

            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.getPixel(i);
            }
            RenderUtil.drawImage(g, image, Integers.windowX, Integers.windowY);

            g.setColor(Color.BLACK);
            g.drawString("FPS: " + CutSceneManager.getFPS() + " - UPS: " + CutSceneManager.getUPS(), Integers.INFO_X, Integers.INFO_Y);

            g.dispose();
            strategy.show();
        }
    }

}
