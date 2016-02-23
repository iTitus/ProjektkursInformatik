package projektkurs.simulation.sudoku.gui;

import java.awt.event.KeyEvent;

import projektkurs.gui.element.Element;
import projektkurs.render.Screen;
import projektkurs.simulation.sudoku.SudokuBoard;

public class ElementSudokuBoard extends Element {

    public static final int CELL_SIZE = 64;

    private final SudokuBoard board;

    public ElementSudokuBoard(int posX, int posY, int id) {
        super(posX, posY, SudokuBoard.SIZE * CELL_SIZE, SudokuBoard.SIZE * CELL_SIZE, id, null);
        this.board = new SudokuBoard();
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (keyChar == 'c') {
            board.calculate();
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
    }

    @Override
    public void update() {
        if (board.canUpdate()) {
            board.update();
        }
    }

}
