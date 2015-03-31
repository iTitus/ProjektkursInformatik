package projektkurs.simulation.mandelbrot.gui;

import projektkurs.gui.element.Element;
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
}
