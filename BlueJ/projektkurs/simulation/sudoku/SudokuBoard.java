package projektkurs.simulation.sudoku;

import java.util.ArrayList;
import java.util.List;

import projektkurs.render.Screen;
import projektkurs.util.IUpdatable;

public class SudokuBoard implements IUpdatable {

    public static final int SIZE = 9;
    public static final int BLOCKS = 3;

    public final SudokuCell[] cells;
    private final SudokuGroup[] rows;
    private final SudokuGroup[] columns;
    private final SudokuGroup[] blocks;

    public SudokuBoard() {
        this.cells = new SudokuCell[SIZE * SIZE];
        this.rows = new SudokuGroup[SIZE];
        this.columns = new SudokuGroup[SIZE];
        this.blocks = new SudokuGroup[SIZE];

        reset();
    }

    public void reset() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                SudokuCell cell = new SudokuCellEditable(this, x, y);
                cells[x + y * SIZE] = cell;
            }
        }

        for (int x = 0; x < SIZE; x++) {
            List<SudokuCell> cellList = new ArrayList<SudokuCell>();
            for (int y = 0; y < SIZE; y++) {
                cellList.add(getCellAt(x, y));
            }
            columns[x] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
        }

        for (int y = 0; y < SIZE; y++) {
            List<SudokuCell> cellList = new ArrayList<SudokuCell>();
            for (int x = 0; x < SIZE; x++) {
                cellList.add(getCellAt(x, y));
            }
            rows[y] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
        }

        for (int yBlock = 0; yBlock < BLOCKS; yBlock++) {
            for (int xBlock = 0; xBlock < BLOCKS; xBlock++) {
                List<SudokuCell> cellList = new ArrayList<SudokuCell>();
                for (int y = 0; y < BLOCKS; y++) {
                    for (int x = 0; x < BLOCKS; x++) {
                        cellList.add(getCellAt(x + xBlock * BLOCKS, y + yBlock * BLOCKS));
                    }
                }
                blocks[xBlock + yBlock * BLOCKS] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
            }
        }

        setNumber(0, 1, Number.SIX);
        setNumber(2, 1, Number.TWO);

        setNumber(3, 2, Number.THREE);
        setNumber(4, 2, Number.ONE);
        setNumber(5, 2, Number.NINE);

        setNumber(8, 0, Number.FOUR);

        setNumber(1, 3, Number.ONE);
        setNumber(2, 3, Number.THREE);
        setNumber(1, 4, Number.NINE);
        setNumber(1, 5, Number.SEVEN);

        setNumber(4, 3, Number.TWO);
        setNumber(3, 4, Number.EIGHT);

        setNumber(6, 4, Number.FIVE);
        setNumber(6, 5, Number.THREE);

        setNumber(0, 6, Number.FOUR);

        setNumber(3, 7, Number.SEVEN);
        setNumber(5, 7, Number.THREE);
        setNumber(3, 8, Number.NINE);
        setNumber(5, 8, Number.SIX);

        setNumber(7, 6, Number.SIX);
        setNumber(7, 7, Number.NINE);
        setNumber(7, 8, Number.EIGHT);
    }

    private void setNumber(int x, int y, Number number) {
        getCellAt(x, y).onNumberInput(number);
    }

    public void calculate() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                SudokuCell cell = getCellAt(x, y);
                if (cell.getNumber() == null) {
                    cells[x + y * SIZE] = new SudokuCellCalculate(this, x, y);
                }
            }
        }

        for (int x = 0; x < SIZE; x++) {
            List<SudokuCell> cellList = new ArrayList<SudokuCell>();
            for (int y = 0; y < SIZE; y++) {
                cellList.add(getCellAt(x, y));
            }
            columns[x] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
        }

        for (int y = 0; y < SIZE; y++) {
            List<SudokuCell> cellList = new ArrayList<SudokuCell>();
            for (int x = 0; x < SIZE; x++) {
                cellList.add(getCellAt(x, y));
            }
            rows[y] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
        }

        for (int yBlock = 0; yBlock < BLOCKS; yBlock++) {
            for (int xBlock = 0; xBlock < BLOCKS; xBlock++) {
                List<SudokuCell> cellList = new ArrayList<SudokuCell>();
                for (int y = 0; y < BLOCKS; y++) {
                    for (int x = 0; x < BLOCKS; x++) {
                        cellList.add(getCellAt(x + xBlock * BLOCKS, y + yBlock * BLOCKS));
                    }
                }
                blocks[xBlock + yBlock * BLOCKS] = new SudokuGroup(cellList.toArray(new SudokuCell[SIZE]));
            }
        }
    }

    public SudokuCell getCellAt(int i) {
        if (i < 0 || i >= cells.length) {
            return null;
        }
        return cells[i];
    }

    public SudokuCell getCellAt(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return null;
        }
        return cells[x + y * SIZE];
    }

    public SudokuGroup getRow(int i) {
        if (i < 0 || i >= SIZE) {
            return null;
        }
        return rows[i];
    }

    public SudokuGroup getColumn(int i) {
        if (i < 0 || i >= SIZE) {
            return null;
        }
        return columns[i];
    }

    public SudokuGroup getBlockAt(int x, int y) {
        return getBlockAtBlockCoord(x / BLOCKS, y / BLOCKS);
    }

    public SudokuGroup getBlockAtBlockCoord(int x, int y) {
        if (x < 0 || y < 0 || x >= BLOCKS || y >= BLOCKS) {
            return null;
        }
        return blocks[x + y * BLOCKS];
    }

    public SudokuGroup getBlock(int i) {
        if (i < 0 || i >= SIZE) {
            return null;
        }
        return blocks[i];
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void update() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                SudokuCell cell = getCellAt(x, y);
                if (cell.canUpdate()) {
                    cell.update();
                }
            }
        }
    }

    public void render(Screen screen, int posX, int posY) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                SudokuCell cell = getCellAt(x, y);
                cell.render(screen, posX, posY);
            }
        }
    }

}
