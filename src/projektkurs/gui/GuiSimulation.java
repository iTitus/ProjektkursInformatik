package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

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
    }
}
