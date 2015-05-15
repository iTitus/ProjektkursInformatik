package projektkurs.simulation.tictactoe.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.gui.element.Element;
import projektkurs.render.Screen;
import projektkurs.simulation.tictactoe.TicTacToeBoard;
import projektkurs.util.RenderUtil;

public class ElementTicTacToe extends Element {

    public static final int SIZE = 128;

    private final TicTacToeBoard board;

    public ElementTicTacToe(int posX, int posY, int id) {
        super(posX, posY, TicTacToeBoard.SIZE * SIZE, TicTacToeBoard.SIZE * SIZE, id, null);
        board = new TicTacToeBoard();
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            board.reset();
        }
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            board.onLeftClick((x - posX) / SIZE, (y - posY) / SIZE);
        }
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
        for (int x = 0; x < sizeX / SIZE; x++) {
            RenderUtil.drawLine(screen, x * SIZE + posX, posY, x * SIZE + posX, posY + sizeY, 0xCCCCCC);
        }
        for (int y = 0; y < sizeY / SIZE; y++) {
            RenderUtil.drawLine(screen, posX, y * SIZE + posY, posX + sizeX, y * SIZE + posY, 0xCCCCCC);
        }
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
    }

    @Override
    public void update() {
        board.update();
    }

}
