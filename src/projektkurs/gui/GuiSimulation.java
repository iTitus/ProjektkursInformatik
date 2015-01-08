package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

/**
 * Ein Simulations-GUI.
 */
public class GuiSimulation extends Gui {

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
        RenderUtil.drawBackground(g, Color.WHITE);
        super.render(g);

        RenderUtil.drawImage(g, Images.strasse_NS, 64, 64, 4 * Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE);
        RenderUtil.drawImage(g, Images.strasse_EW, 256, 64, 8 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE);
    }
}
