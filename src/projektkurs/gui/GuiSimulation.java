package projektkurs.gui;

import projektkurs.lib.Sprites;
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

        RenderUtil.drawSprite(screen, Sprites.nuke, 64, 64);
        RenderUtil.drawSprite(screen, Sprites.nuke_2, 64, 128);
        RenderUtil.drawSprite(screen, Sprites.nuke_3, 64, 192);
        RenderUtil.drawSprite(screen, Sprites.nuke_4, 64, 256);

        super.render(screen);
    }
}
