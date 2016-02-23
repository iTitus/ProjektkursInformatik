package projektkurs.simulation.sudoku.gui;

import projektkurs.gui.Gui;

public class GuiSudoku extends Gui {

    private ElementSudokuBoard elementSudokuBoard;

    @Override
    public void initGui() {
        super.initGui();
        elementSudokuBoard = new ElementSudokuBoard(64, 64, 0);
        addElement(elementSudokuBoard);
    }

}
