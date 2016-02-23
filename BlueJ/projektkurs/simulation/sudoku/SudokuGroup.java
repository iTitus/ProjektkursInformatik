package projektkurs.simulation.sudoku;

import java.util.Iterator;

public class SudokuGroup implements Iterable<SudokuCell> {

    private final SudokuCell[] cells;

    public SudokuGroup(SudokuCell... cells) {
        if (cells == null || cells.length != SudokuBoard.SIZE) {
            throw new IllegalArgumentException("9 cells per group");
        }
        this.cells = cells;
    }

    public SudokuCell getCellAt(int i) {
        if (i < 0 || i >= SudokuBoard.SIZE) {
            return null;
        }
        return cells[i];
    }

    @Override
    public Iterator<SudokuCell> iterator() {
        return new Iterator<SudokuCell>() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < SudokuBoard.SIZE;
            }

            @Override
            public SudokuCell next() {
                return cells[cursor++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    public void markSet(Number number) {
        for (SudokuCell cell : cells) {
            cell.markSet(number);
        }
    }

}
