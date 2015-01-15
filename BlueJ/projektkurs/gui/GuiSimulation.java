package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Ein Simulations-GUI.
 */
public class GuiSimulation extends Gui {

    private final BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    /**
     * Konstruktor.
     */
    public GuiSimulation() {
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void render(Graphics2D g) {
        // RenderUtil.drawBackground(g, Color.WHITE);
        // super.render(g);

        // Arrays.fill(pixels, 0);

        Arrays.fill(pixels, MathUtil.randomInt(0xFFFFFF));

        RenderUtil.drawImage(g, image, 64, 64);
    }
}
