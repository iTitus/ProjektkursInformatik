package projektkurs.simulation.pacman.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiPacman extends Gui {

    private ElementPacmanBoard elementPacmanBoard;

    @Override
    public void initGui() {
        super.initGui();
        elementPacmanBoard = new ElementPacmanBoard(64, 64, 28, 31, 0);
        addElement(elementPacmanBoard);
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}
