package projektkurs.simulation.mandelbrot.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.lib.KeyBindings;
import projektkurs.render.Screen;
import projektkurs.simulation.mandelbrot.BoardMandelbrotSet;
import projektkurs.util.RenderUtil;

public class ElementMandelbrotSet extends Element {

    private final BoardMandelbrotSet board;

    public ElementMandelbrotSet(int posX, int posY, int sizeX, int sizeY, int id, double c_r, double c_i, boolean brot) {
        super(posX, posY, sizeX, sizeY, id, null);
        board = new BoardMandelbrotSet(sizeX, sizeY, c_r, c_i, brot);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (keyChar == 'r') {
            board.setPos(0, 0);
            board.setZoom(1);
            if (board.isBrot()) {
                board.setIteration(257);
            }
        }
    }

    @Override
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        if (by < 0 && board.getZoom() > 0) {
            board.increaseZoom(2);
        } else if (by > 0) {
            board.decreaseZoom(2);
        }
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
    }

    public void start() {
        board.start();
    }

    public void stop() {
        board.stop();
    }

    @Override
    public void update() {
        double dX = 0;
        double dY = 0;
        if (Main.getInputManager().isKeyPressed(KeyBindings.KEY_UP)) {
            dY -= (board.getMaxY() + board.getMinY()) / 100.;
        }
        if (Main.getInputManager().isKeyPressed(KeyBindings.KEY_LEFT)) {
            dX -= (board.getMaxX() + board.getMinX()) / 100.;
        }
        if (Main.getInputManager().isKeyPressed(KeyBindings.KEY_DOWN)) {
            dY += (board.getMaxY() + board.getMinY()) / 100.;
        }
        if (Main.getInputManager().isKeyPressed(KeyBindings.KEY_RIGHT)) {
            dX += (board.getMaxX() + board.getMinX()) / 100.;
        }
        board.move(dX / board.getZoom(), dY / board.getZoom());
    }
}
