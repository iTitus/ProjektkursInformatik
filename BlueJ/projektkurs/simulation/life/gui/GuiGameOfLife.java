package projektkurs.simulation.life.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

/**
 * Ein Logik-Simulations-GUI.
 */
public class GuiGameOfLife extends Gui {

    private ElementGameOfLife gameOfLifeElement;

    /**
     * Konstruktor.
     */
    public GuiGameOfLife() {
    }

    @Override
    public void initGui() {
        super.initGui();
        gameOfLifeElement = new ElementGameOfLife(64, 64, 384, 192, 0);
        addElement(gameOfLifeElement);
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }
}
