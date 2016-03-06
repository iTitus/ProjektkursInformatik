package projektkurs.simulation.sudoku;

import java.util.Set;

import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.sudoku.gui.ElementSudokuBoard;
import projektkurs.util.RenderUtil;

public class SudokuCellCalculate extends SudokuCell {

    private final Set<Number> possibleNumbers;
    private Number number;

    public SudokuCellCalculate(SudokuBoard board, int x, int y) {
        super(board, x, y);
        possibleNumbers = Number.getAll();
    }

    @Override
    public boolean canUpdate() {
        return number == null;
    }

    @Override
    public Number getNumber() {
        return number;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void markSet(Number number) {
        possibleNumbers.remove(number);
    }

    @Override
    public void onNumberInput(Number number) {
        // NO-OP
    }

    @Override
    public void render(Screen screen, int posX, int posY) {
        RenderUtil.drawRectangle(screen, posX + x * ElementSudokuBoard.CELL_SIZE, posY + y * ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE);
        if (number != null) {
            Font.drawCenteredStringInRect(screen, number.getNumberString(), posX + x * ElementSudokuBoard.CELL_SIZE, posY + y * ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE, ElementSudokuBoard.CELL_SIZE);
        }
    }

    @Override
    public void update() {
        if (possibleNumbers.size() > 1) {
            SudokuGroup column = board.getColumn(x);
            SudokuGroup row = board.getRow(y);
            SudokuGroup block = board.getBlockAt(x, y);

            removePossibles(column);
            removePossibles(row);
            removePossibles(block);

            if (possibleNumbers.size() > 1) {
                for (Number number : possibleNumbers) {
                    if (checkOnlyPossibility(number, column) || checkOnlyPossibility(number, row) || checkOnlyPossibility(number, block)) {
                        this.number = number;
                        row.markSet(number);
                        column.markSet(number);
                        block.markSet(number);
                        return;
                    }
                }
            }

        } else if (possibleNumbers.size() == 1) {
            SudokuGroup column = board.getColumn(x);
            SudokuGroup row = board.getRow(y);
            SudokuGroup block = board.getBlockAt(x, y);

            number = possibleNumbers.iterator().next();

            row.markSet(number);
            column.markSet(number);
            block.markSet(number);
        }
    }

    private boolean checkOnlyPossibility(Number number, SudokuGroup group) {
        for (SudokuCell cell : group) {
            if (cell != null && cell != this && cell instanceof SudokuCellCalculate) {
                for (Number possibleNumber : ((SudokuCellCalculate) cell).possibleNumbers) {
                    if (number == possibleNumber) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void removePossibles(SudokuGroup group) {
        for (SudokuCell cell : group) {
            if (cell != null && cell != this) {
                Number number = cell.getNumber();
                if (number != null) {
                    possibleNumbers.remove(number);
                }
            }
        }
    }

}
