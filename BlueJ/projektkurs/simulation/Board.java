package projektkurs.simulation;

import projektkurs.render.Screen;
import projektkurs.util.IUpdatable;

public class Board implements IUpdatable {

    private EnumFlow[][] boardFlows;
    private final Rule[][] boardRules;
    private final int sizeX, sizeY;

    public Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        boardFlows = new EnumFlow[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                boardFlows[x][y] = EnumFlow.NONE;
            }
        }
        boardRules = new Rule[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                boardRules[x][y] = new NothingRule();
            }
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public EnumFlow getFlow(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y <= sizeY) {
            return boardFlows[x][y];
        }
        return EnumFlow.NONE;
    }

    public Rule getRule(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y <= sizeY) {
            return boardRules[x][y];
        }
        return new NothingRule();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void render(Screen screen, int offsetX, int offsetY) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                getRule(x, y).render(screen, this, x, y, offsetX, offsetY);
            }
        }
    }

    public void setRule(Rule rule, int x, int y) {
        boardRules[x][y] = rule;
    }

    @Override
    public void update() {
        EnumFlow[][] temp = new EnumFlow[sizeX][sizeY];
        for (int x = 0; x < boardRules.length; x++) {
            for (int y = 0; y < boardRules[x].length; y++) {
                temp[x][y] = boardRules[x][y].getNextFlow(this, x, y);
            }
        }
        boardFlows = temp;
    }

}
