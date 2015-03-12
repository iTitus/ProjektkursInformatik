package projektkurs.gui.element;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.AndGateRule;
import projektkurs.simulation.BlinkSource;
import projektkurs.simulation.Board;
import projektkurs.simulation.NotGateRule;
import projektkurs.simulation.NothingRule;
import projektkurs.simulation.OmniDirWireRule;
import projektkurs.simulation.OmniSourceRule;
import projektkurs.simulation.OrGateRule;
import projektkurs.simulation.Rule;
import projektkurs.simulation.SimpleWireRule;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class SimulationBoard extends Element {

    public static final int SIZE = 16;
    private static final int TYPES = 17;
    private final Board board;
    private int index, oldX = -1, oldY = -1;

    /**
     * @param posX
     * @param posY
     * @param sizeX
     * @param sizeY
     * @param id
     * @param listener
     */
    public SimulationBoard(int posX, int posY, int sizeX, int sizeY, int id, ISimulationBoardListener listener) {
        super(posX, posY, sizeX * SIZE, sizeY * SIZE, id, listener);
        board = new Board(sizeX, sizeY);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Board getBoard() {
        return board;
    }

    public int getFlow(int x, int y) {
        return board.getFlow(x, y);
    }

    @Override
    public ISimulationBoardListener getListener() {
        return (ISimulationBoardListener) super.getListener();
    }

    public Rule getRule(int x, int y) {
        return board.getRule(x, y);
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        int boardX = (x - posX) / SIZE;
        int boardY = (y - posY) / SIZE;
        if (boardX >= 0 && boardY >= 0 && boardX < board.getSizeX() && boardY < board.getSizeY()) {
            if (e.isShiftDown()) {
                if (oldX >= 0 && oldX < sizeX && oldY >= 0 && oldY < sizeY) {
                    if (index == 1) {
                        for (int i = oldX < boardX ? oldX : boardX; i <= (oldX < boardX ? boardX : oldX); i++) {
                            setRule(new SimpleWireRule(true), i, boardY);
                        }
                    } else if (index == 2) {
                        for (int i = oldY < boardY ? oldY : boardY; i <= (oldY < boardY ? boardY : oldY); i++) {
                            setRule(new SimpleWireRule(false), boardX, i);
                        }
                    }
                    oldX = -1;
                    oldY = -1;
                } else {
                    oldX = boardX;
                    oldY = boardY;
                }
                return;
            }
            oldX = -1;
            oldY = -1;
            Rule rule = null;
            switch (index) {
                case 0:
                    rule = new OmniSourceRule();
                    break;
                case 1:
                    rule = new SimpleWireRule(true);
                    break;
                case 2:
                    rule = new SimpleWireRule(false);
                    break;
                case 3:
                    rule = new OmniDirWireRule();
                    break;
                case 4:
                    rule = new AndGateRule(1);
                    break;
                case 5:
                    rule = new AndGateRule(2);
                    break;
                case 6:
                    rule = new AndGateRule(3);
                    break;
                case 7:
                    rule = new AndGateRule(4);
                    break;
                case 8:
                    rule = new OrGateRule(1);
                    break;
                case 9:
                    rule = new OrGateRule(2);
                    break;
                case 10:
                    rule = new OrGateRule(3);
                    break;
                case 11:
                    rule = new OrGateRule(4);
                    break;
                case 12:
                    rule = new NotGateRule(1);
                    break;
                case 13:
                    rule = new NotGateRule(2);
                    break;
                case 14:
                    rule = new NotGateRule(3);
                    break;
                case 15:
                    rule = new NotGateRule(4);
                    break;
                case 16:
                    rule = new BlinkSource(1, 4);
                    break;
                default:
                    break;
            }
            if (rule != null) {
                setRule(rule, boardX, boardY);
            }
        }
    }

    @Override
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        if (by > 0) {
            index++;
            if (index >= TYPES) {
                index = 0;
            }
        } else if (by < 0) {
            index--;
            if (index < 0) {
                index = TYPES - 1;
            }
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        int boardX = (x - posX) / SIZE;
        int boardY = (y - posY) / SIZE;
        if (boardX >= 0 && boardY >= 0 && boardX < board.getSizeX() && boardY < board.getSizeY()) {
            setRule(new NothingRule(), boardX, boardY);
        }
    }

    @Override
    public void render(Screen screen) {
        for (int y = 0; y < board.getSizeY(); y++) {
            for (int x = 0; x < board.getSizeX(); x++) {
                RenderUtil.drawFilledRectangle(screen, x * SIZE + posX, y * SIZE + posY, SIZE, SIZE, getRule(x, y).getColor(board, x, y));
            }
        }
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
        String desc = "";
        switch (index) {
            case 0:
                desc = new OmniSourceRule().getName();
                break;
            case 1:
                desc = new SimpleWireRule(true).getName();
                break;
            case 2:
                desc = new SimpleWireRule(false).getName();
                break;
            case 3:
                desc = new OmniDirWireRule().getName();
                break;
            case 4:
                desc = new AndGateRule(1).getName();
                break;
            case 5:
                desc = new AndGateRule(2).getName();
                break;
            case 6:
                desc = new AndGateRule(3).getName();
                break;
            case 7:
                desc = new AndGateRule(4).getName();
                break;
            case 8:
                desc = new OrGateRule(1).getName();
                break;
            case 9:
                desc = new OrGateRule(2).getName();
                break;
            case 10:
                desc = new OrGateRule(3).getName();
                break;
            case 11:
                desc = new OrGateRule(4).getName();
                break;
            case 12:
                desc = new NotGateRule(1).getName();
                break;
            case 13:
                desc = new NotGateRule(2).getName();
                break;
            case 14:
                desc = new NotGateRule(3).getName();
                break;
            case 15:
                desc = new NotGateRule(4).getName();
                break;
            case 16:
                desc = new BlinkSource(1, 4).getName();
                break;
            default:
                break;
        }
        Font.drawString(screen, "Current Rule: " + index + " (" + desc + ")", posX, posY);
    }

    @Override
    public void renderTooltip(Screen screen) {
        Rule hovered = getRule((Main.getInputManager().getMouseX() - posX) / SIZE, (Main.getInputManager().getMouseY() - posY) / SIZE);
        if (hovered != null) {
            String name = hovered.getName();
            if (name != null && name.length() > 0) {
                RenderUtil.drawTooltip(screen, name, Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY());
            }
        }
    }

    public void setRule(Rule rule, int x, int y) {
        board.setRule(rule, x, y);
    }

    @Override
    public void update() {
        if (board.canUpdate()) {
            board.update();
        }
    }

}
