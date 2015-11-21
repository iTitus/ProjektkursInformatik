package projektkurs.simulation.pacman.gui;

import projektkurs.gui.Gui;

public class GuiPacman extends Gui {

    private ElementPacmanBoard elementPacmanBoard;

    @Override
    public void initGui() {
        super.initGui();
        elementPacmanBoard = new ElementPacmanBoard(64, 64, 28, 31, 0);
        addElement(elementPacmanBoard);
    }

}
