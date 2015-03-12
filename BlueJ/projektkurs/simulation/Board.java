package projektkurs.simulation;

import projektkurs.util.IUpdatable;

public class Board implements IUpdatable {

    /**
     * 0: no Flow; 1: Flow SN; 2: Flow WE; 3: Flow NS; 4: Flow EW; -1: Omnidirectional
     */
    private int[][] boardFlows;
    private final Rule[][] boardRules;
    private final int sizeX, sizeY;

    public Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        boardFlows = new int[sizeX + 2][sizeY + 2];
        boardRules = new Rule[sizeX][sizeY];
        for (int i = 0; i < boardRules.length; i++) {
            for (int j = 0; j < boardRules[i].length; j++) {
                boardRules[i][j] = new NothingRule();
            }
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public int getFlow(int x, int y) {
        return boardFlows[x + 1][y + 1];
    }

    public Rule getRule(int x, int y) {
        return boardRules[x][y];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setRule(Rule rule, int x, int y) {
        boardRules[x][y] = rule;
    }

    @Override
    public void update() {
        int[][] temp = new int[sizeX + 2][sizeY + 2];
        for (int x = 0; x < temp.length; x++) {
            for (int y = 0; y < temp[x].length; y++) {
                temp[x][y] = boardFlows[x][y];
            }
        }

        for (int x = 0; x < boardRules.length; x++) {
            for (int y = 0; y < boardRules[x].length; y++) {
                temp[x + 1][y + 1] = boardRules[x][y].nextInt(this, x, y);
            }
        }
        boardFlows = temp;
    }
}
