package projektkurs.simulation.tower.gui;

import java.awt.event.MouseEvent;

import projektkurs.gui.element.Element;
import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.TowerDefenseBoardType;
import projektkurs.util.RenderUtil;

public class ElementTowerDefenseBoard extends Element {

    public static final int SIZE = 16;

    private final TowerDefenseBoard board;

    public ElementTowerDefenseBoard(int posX, int posY, TowerDefenseBoardType boardType, int id) {
        super(posX, posY, boardType.getSizeX() * SIZE, boardType.getSizeY() * SIZE, id, null);
        board = new TowerDefenseBoard(boardType);
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            board.onLeftClick(x, y, e);
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            board.onRightClick(x, y, e);
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
    }

    @Override
    public void update() {
        if (board.canUpdate()) {
            board.update();
        }
    }
}
