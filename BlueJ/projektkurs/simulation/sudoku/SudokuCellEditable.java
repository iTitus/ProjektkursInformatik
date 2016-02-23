package projektkurs.simulation.sudoku;

import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.sudoku.gui.ElementSudokuBoard;
import projektkurs.util.RenderUtil;

public class SudokuCellEditable extends SudokuCell {

    private Number number;

    public SudokuCellEditable(SudokuBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void update() {
        // NO-OP
    }

    @Override
    public void render(Screen screen, int posX, int posY) {
        RenderUtil.drawFilledRectangle(screen, posX + x * ElementSudokuBoard.CELL_SIZE, posY + y * ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, 0xBBEEFF);
        RenderUtil.drawRectangle(screen, posX + x * ElementSudokuBoard.CELL_SIZE, posY + y * ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE);
        if (number != null) {
            Font.drawCenteredStringInRect(screen, number.getNumberString(), posX + x * ElementSudokuBoard.CELL_SIZE, posY + y * ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE);
        }
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void onNumberInput(Number number) {
        this.number = number;
    }

    @Override
    public Number getNumber() {
        return number;
    }

    @Override
    public void markSet(Number number) {
        // NO-OP
    }

}
