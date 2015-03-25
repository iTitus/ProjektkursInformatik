package projektkurs.simulation.mandelbrot;

import java.util.concurrent.atomic.AtomicIntegerArray;

import projektkurs.render.Font;
import projektkurs.render.Screen;

public class BoardMandelbrotSet {

    private final AtomicIntegerArray board;
    private final MandelbrotIteratorThread mandelbrotIteratorThread;
    private final int sizeX, sizeY;
    private final double x, y;

    public BoardMandelbrotSet(int sizeX, int sizeY, double x, double y) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mandelbrotIteratorThread = new MandelbrotIteratorThread(this);
        board = new AtomicIntegerArray(sizeX * sizeY);
        this.x = x;
        this.y = y;
    }

    public int get(int x, int y) {
        return board.get(x + y * sizeX);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void render(Screen screen, int posX, int posY) {
        Font.drawString(screen, "Iteration: " + mandelbrotIteratorThread.getIteration(), posX, posY, 0x0000DD);
    }

    public void set(int x, int y, int i) {
        board.lazySet(x + y * sizeX, i);
    }

    public void start() {
        mandelbrotIteratorThread.start();
    }

    public void stop() {
        mandelbrotIteratorThread.requestStop();
    }
}
