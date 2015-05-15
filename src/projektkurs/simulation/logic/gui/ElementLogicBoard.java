package projektkurs.simulation.logic.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

import projektkurs.Main;
import projektkurs.gui.Gui;
import projektkurs.gui.element.Element;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.simulation.logic.rule.AndGateRule;
import projektkurs.simulation.logic.rule.BlinkSourceRule;
import projektkurs.simulation.logic.rule.NotGateRule;
import projektkurs.simulation.logic.rule.NothingRule;
import projektkurs.simulation.logic.rule.OmniDirWireRule;
import projektkurs.simulation.logic.rule.OmniSourceRule;
import projektkurs.simulation.logic.rule.OneWayWireRule;
import projektkurs.simulation.logic.rule.OrGateRule;
import projektkurs.simulation.logic.rule.Rule;
import projektkurs.simulation.logic.rule.SimpleWireRule;
import projektkurs.simulation.logic.rule.XOrGateRule;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class ElementLogicBoard extends Element {

    public static final int SIZE = 16;
    private static final Rule[] TYPES = { new OmniSourceRule(), new BlinkSourceRule(1, 4), new BlinkSourceRule(1, 1), new BlinkSourceRule(2, 2), new SimpleWireRule(true), new SimpleWireRule(false), new OneWayWireRule(EnumFlow.NORTH), new OneWayWireRule(EnumFlow.EAST), new OneWayWireRule(EnumFlow.SOUTH),
            new OneWayWireRule(EnumFlow.WEST), new OmniDirWireRule(), new NotGateRule(EnumFlow.NORTH), new NotGateRule(EnumFlow.EAST), new NotGateRule(EnumFlow.SOUTH), new NotGateRule(EnumFlow.WEST), new AndGateRule(EnumFlow.NORTH), new AndGateRule(EnumFlow.EAST), new AndGateRule(EnumFlow.SOUTH),
            new AndGateRule(EnumFlow.WEST), new OrGateRule(EnumFlow.NORTH), new OrGateRule(EnumFlow.EAST), new OrGateRule(EnumFlow.SOUTH), new OrGateRule(EnumFlow.WEST), new XOrGateRule(EnumFlow.NORTH), new XOrGateRule(EnumFlow.EAST), new XOrGateRule(EnumFlow.SOUTH), new XOrGateRule(EnumFlow.WEST) };
    private int index, oldX = -1, oldY = -1;
    private final LogicBoard logicBoard;

    public ElementLogicBoard(int posX, int posY, int sizeX, int sizeY, int id) {
        super(posX, posY, sizeX * SIZE, sizeY * SIZE, id, null);
        logicBoard = new LogicBoard(sizeX, sizeY);
    }

    @Override
    public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
        Rule hovered = getRule((mouseX - posX) / SIZE, (mouseY - posY) / SIZE);
        if (hovered != null) {
            String name = hovered.getName();
            if (name != null && name.length() > 0) {
                tooltip.add(name);
            }
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public LogicBoard getBoard() {
        return logicBoard;
    }

    public EnumFlow getFlow(int x, int y) {
        return logicBoard.getFlow(x, y);
    }

    public Rule getRule(int x, int y) {
        return logicBoard.getRule(x, y);
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        int boardX = (x - posX) / SIZE;
        int boardY = (y - posY) / SIZE;
        if (boardX >= 0 && boardY >= 0 && boardX < logicBoard.getSizeX() && boardY < logicBoard.getSizeY()) {
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
        if (boardX >= 0 && boardY >= 0 && boardX < logicBoard.getSizeX() && boardY < logicBoard.getSizeY()) {
            setRule(new NothingRule(), boardX, boardY);
        }
    }

    @Override
    public void render(Screen screen) {
        logicBoard.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
        if (isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
            RenderUtil.drawRectangle(screen, SIZE * ((Main.getInputManager().getMouseX() - posX) / SIZE) + posX, SIZE * ((Main.getInputManager().getMouseY() - posY) / SIZE) + posY, SIZE, SIZE);
        }
        Font.drawString(screen, "Current Rule: " + index + " (" + TYPES[index].getName() + ")", posX, posY);
    }

    public void setRule(Rule rule, int x, int y) {
        logicBoard.setRule(rule, x, y);
    }

    @Override
    public void update() {
        if (logicBoard.canUpdate()) {
            logicBoard.update();
        }
    }

}
