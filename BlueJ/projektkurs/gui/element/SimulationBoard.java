package projektkurs.gui.element;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.AndGateRule;
import projektkurs.simulation.BlinkSource;
import projektkurs.simulation.Board;
import projektkurs.simulation.EnumFlow;
import projektkurs.simulation.NotGateRule;
import projektkurs.simulation.NothingRule;
import projektkurs.simulation.OmniDirWireRule;
import projektkurs.simulation.OmniSourceRule;
import projektkurs.simulation.OneWayWireRule;
import projektkurs.simulation.OrGateRule;
import projektkurs.simulation.Rule;
import projektkurs.simulation.SimpleWireRule;
import projektkurs.simulation.XOrGateRule;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class SimulationBoard extends Element {

    public static final int SIZE = 16;
    private static final Rule[] TYPES = { new OmniSourceRule(), new BlinkSource(1, 4), new BlinkSource(1, 1), new BlinkSource(2, 2), new SimpleWireRule(true), new SimpleWireRule(false), new OneWayWireRule(EnumFlow.NORTH), new OneWayWireRule(EnumFlow.EAST), new OneWayWireRule(EnumFlow.SOUTH),
            new OneWayWireRule(EnumFlow.WEST), new OmniDirWireRule(), new NotGateRule(EnumFlow.NORTH), new NotGateRule(EnumFlow.EAST), new NotGateRule(EnumFlow.SOUTH), new NotGateRule(EnumFlow.WEST), new AndGateRule(EnumFlow.NORTH), new AndGateRule(EnumFlow.EAST), new AndGateRule(EnumFlow.SOUTH),
            new AndGateRule(EnumFlow.WEST), new OrGateRule(EnumFlow.NORTH), new OrGateRule(EnumFlow.EAST), new OrGateRule(EnumFlow.SOUTH), new OrGateRule(EnumFlow.WEST), new XOrGateRule(EnumFlow.NORTH), new XOrGateRule(EnumFlow.EAST), new XOrGateRule(EnumFlow.SOUTH), new XOrGateRule(EnumFlow.WEST) };
    private final Board board;
    private int index, oldX = -1, oldY = -1;

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

    public EnumFlow getFlow(int x, int y) {
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
                    if (index == 4) {
                        for (int i = oldX < boardX ? oldX : boardX; i <= (oldX < boardX ? boardX : oldX); i++) {
                            setRule(new SimpleWireRule(true), i, oldY);
                        }
                    } else if (index == 5) {
                        for (int i = oldY < boardY ? oldY : boardY; i <= (oldY < boardY ? boardY : oldY); i++) {
                            setRule(new SimpleWireRule(false), oldX, i);
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
            setRule(TYPES[index].copy(), boardX, boardY);
        }
    }

    @Override
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        if (by > 0) {
            index++;
            if (index >= TYPES.length) {
                index = 0;
            }
        } else if (by < 0) {
            index--;
            if (index < 0) {
                index = TYPES.length - 1;
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
        board.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
        if (isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
            RenderUtil.drawRectangle(screen, SIZE * ((Main.getInputManager().getMouseX() - posX) / SIZE) + posX, SIZE * ((Main.getInputManager().getMouseY() - posY) / SIZE) + posY, SIZE, SIZE);
        }
        Font.drawString(screen, "Current Rule: " + index + " (" + TYPES[index].getName() + ")", posX, posY);
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
