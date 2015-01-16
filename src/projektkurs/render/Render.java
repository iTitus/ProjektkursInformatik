package projektkurs.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.IUpdatable;
import projektkurs.util.RenderUtil;

/**
 * Renderklasse.
 */
public class Render implements IUpdatable {

    /**
     * Das Spiel-Canvas.
     */
    private Canvas canvas;
    /**
     * Das aktuelle Graphics2D-Objekt.
     */
    private Graphics2D g;
    /**
     * Bild.
     */
    private final BufferedImage image = new BufferedImage(Integers.windowX, Integers.windowY, BufferedImage.TYPE_INT_RGB);
    /**
     * Die zum Bild gehörigen Pixel.
     */
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    /**
     * Der Screen.
     */
    private final Screen screen;
    /**
     * Die aktuelle BufferStrategy.
     */
    private BufferStrategy strategy;

    /**
     * Konstruktor.
     */
    public Render() {
        canvas = null;
        strategy = null;
        g = null;
        screen = new Screen(Integers.windowX, Integers.windowY);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Die aktuelle BufferStrategy.
     *
     * @return BufferStrategy
     */
    public BufferStrategy getBufferStrategy() {
        return strategy;
    }

    /**
     * Gibt das aktuelle Canvas zurück.
     *
     * @return Canvas
     */
    public Canvas getGameCanvas() {
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setIgnoreRepaint(true);
            canvas.setBounds(0, 0, Integers.windowX, Integers.windowY);
            canvas.addKeyListener(Main.getInputManager());
            canvas.addMouseListener(Main.getInputManager());
            canvas.addMouseMotionListener(Main.getInputManager());
            canvas.addMouseWheelListener(Main.getInputManager());
            canvas.setFocusable(true);
            canvas.requestFocus();
            canvas.requestFocusInWindow();
        }
        return canvas;
    }

    /**
     * Initialisiert die BufferStrategy.
     */
    public void initBuffers() {
        canvas.createBufferStrategy(3);
        strategy = canvas.getBufferStrategy();
    }

    /**
     * Updated den Bildschirm.
     */
    @Override
    public void update() {
        if (strategy != null) {

            g = (Graphics2D) strategy.getDrawGraphics();

            screen.clear();

            if (Main.getLevel() != null) {
                Main.getLevel().render(screen);
            }

            g.setColor(Color.BLACK);
            Main.getGui().render(screen);

            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.getPixel(i);
            }
            RenderUtil.drawImage(g, image, Integers.windowX, Integers.windowY);

            g.drawString("FPS: " + Main.getFPS() + " - UPS: " + Main.getUPS() + (Main.getLevel() != null && Main.getPlayer() != null ? " | X: " + Main.getPlayer().getPosX() + " - Y: " + Main.getPlayer().getPosY() + " | Health: " + Main.getPlayer().getHealth() + " / " + Main.getPlayer().getMaxHealth() : ""),
                    Integers.INFO_X, Integers.INFO_Y);

            g.dispose();
            strategy.show();

        }
    }
}
