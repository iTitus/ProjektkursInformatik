package projektkurs.cutscene.render;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.I18n;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.StringUtil;

/**
 * CutScene-Renderklasse.
 */
public class CutsceneRender extends Canvas implements IUpdatable {

    /**
     * Serielle Version.
     */
    private static final long serialVersionUID = 1L;
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
     * Konstruktor.
     */
    public CutsceneRender() {
        setIgnoreRepaint(true);
        setBounds(0, 0, Integers.windowX, Integers.windowY);
        addKeyListener(Main.getInputManager());
        addMouseListener(Main.getInputManager());
        addMouseMotionListener(Main.getInputManager());
        addMouseWheelListener(Main.getInputManager());
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
        screen = new Screen(Integers.windowX, Integers.windowY);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Rendert die aktuelle CutScene.
     */
    @Override
    public void update() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

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

        Font.drawString(screen, "FPS: " + CutSceneManager.getFPS() + " - UPS: " + CutSceneManager.getUPS(), Integers.INFO_X, Integers.INFO_Y, 0x0000FF);
        if (StringUtil.isNotNullOrEmpty(CutSceneManager.getCutScene().getCaptionString())) {
            Font.drawCenteredString(screen, I18n.getString(CutSceneManager.getCutScene().getCaptionString()), MathUtil.floorDiv(Integers.windowX, 2), Integers.windowY - MathUtil.floorDiv(Integers.WINDOW_HUD_Y, 2), 0xFFFFFF);
        }

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixel(i);
        }

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        RenderUtil.drawImage(g, image, 0, 0, Integers.windowX, Integers.windowY);
        g.dispose();
        strategy.show();
    }

}
