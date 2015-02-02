package projektkurs.render;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Renderklasse.
 */
public class Render extends Canvas implements IUpdatable {

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
     * Der Screen.
     */
    private final Screen screen;

    /**
     * Konstruktor.
     */
    public Render() {
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
     * Updated den Bildschirm.
     */
    @Override
    public void update() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        if (Main.getLevel() != null) {
            Main.getLevel().render(screen);
        }

        Main.getGui().render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixel(i);
        }

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        RenderUtil.drawImage(g, image, 0, 0, getWidth(), getHeight());

        g.drawString(
                "FPS: "
                        + Main.getFPS()
                        + " - UPS: "
                        + Main.getUPS()
                        + (Main.getLevel() != null && Main.getPlayer() != null ? " | X: " + Main.getPlayer().getPosX() + " - Y: " + Main.getPlayer().getPosY() + " | Health: " + Main.getPlayer().getHealth() + " / " + Main.getPlayer().getMaxHealth() + " | Facing: " + Main.getPlayer().getFacing() + " ("
                                + MathUtil.floorDiv(Main.getPlayer().getFacing().getIndex(), 2) + ")" : ""), Integers.INFO_X, Integers.INFO_Y);

        g.dispose();
        strategy.show();
    }
}
