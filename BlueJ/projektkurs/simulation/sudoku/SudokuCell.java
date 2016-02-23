package projektkurs.simulation.sudoku;

import projektkurs.render.Screen;
import projektkurs.util.IUpdatable;

public abstract class SudokuCell implements IUpdatable {

    protected final SudokuBoard board;
    protected final int x, y;

    public SudokuCell(SudokuBoard board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    public abstract void render(Screen screen, int posX, int posY);

    public abstract boolean isSelectable();

    public abstract void onNumberInput(Number number);

    public abstract Number getNumber();

    public SudokuBoard getBoard() {
        return board;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void markSet(Number number);

}
