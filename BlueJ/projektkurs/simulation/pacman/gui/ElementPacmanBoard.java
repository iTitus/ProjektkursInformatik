package projektkurs.simulation.pacman.gui;

import java.awt.event.KeyEvent;

import projektkurs.gui.element.Element;
import projektkurs.lib.KeyBindings;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

public class ElementPacmanBoard extends Element {

    public static final int SIZE = 16;

    private final PacmanBoard board;

    public ElementPacmanBoard(int posX, int posY, int sizeX, int sizeY, int id) {
        super(posX, posY, sizeX * SIZE, sizeY * SIZE, id, null);
        board = new PacmanBoard(sizeX, sizeY);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (e.getKeyCode() == KeyBindings.KEY_UP) {
            board.getPacman().setNextDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyBindings.KEY_LEFT) {
            board.getPacman().setNextDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyBindings.KEY_DOWN) {
            board.getPacman().setNextDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyBindings.KEY_RIGHT) {
            board.getPacman().setNextDirection(Direction.RIGHT);
        }
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
