package projektkurs.gui;

import projektkurs.render.Screen;
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
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }
}
