package projektkurs.simulation.mandelbrot;

import java.util.concurrent.atomic.AtomicInteger;

public class MandelbrotIteratorThread extends Thread {

    private final BoardMandelbrotSet board;
    private final AtomicInteger iteration;
    private volatile boolean stopRequested = false;

    public MandelbrotIteratorThread(BoardMandelbrotSet board) {
        super("Mandelbrot Set Iterator");
        this.board = board;
        iteration = new AtomicInteger();
    }

    public int getIteration() {
        return iteration.get();
    }

    public double getMaxX() {
        return board.isBrot() ? 1.5 : 2.25;
    }

    public double getMaxY() {
        return 1.25;
    }

    public double getMinX() {
        return board.isBrot() ? 3 : 2.25;
    }

    public double getMinY() {
        return 1.25;
    }

    public void requestStop() {
        stopRequested = true;
    }

    @Override
    public void run() {
        while (!stopRequested) {
            if (!board.isBrot() || iteration.get() <= 256) {
                iteration.getAndIncrement();
            }
            for (int y = 0; y < board.getSizeY(); y++) {
                double i = y * (getMinY() + getMaxY()) / board.getSizeY() - getMinY();
                for (int x = 0; x < board.getSizeX(); x++) {
                    double r = x * (getMinX() + getMaxX()) / board.getSizeX() - getMinX();
                    if (stopRequested) {
                        return;
                    }
                    board.set(x, y, isInMandelbrotSet(r, i, board.getC_r(), board.getC_i()));
                }
            }
        }
    }

    private int isInMandelbrotSet(double x, double y, double r, double i) {
        int max;
        if (board.isBrot()) {
            double x_r = 0, x_i = 0;
            max = iteration.get();
            for (int n = 0; n < max; n++) {
                double temp = x_r * x_r - x_i * x_i + x;
                x_i = 2 * x_r * x_i + y;
                x_r = temp;
                if (x_r * x_r + x_i * x_i >= 4) {
                    return n;
                }
            }
        } else {
            double x_r = x, x_i = y;
            max = iteration.get();
            for (int n = 0; n < max; n++) {
                double temp = x_r * x_r - x_i * x_i + r;
                x_i = 2 * x_r * x_i + i;
                x_r = temp;
                if (x_r * x_r + x_i * x_i >= 4) {
                    return n;
                }
            }
        }
        return max;
    }
}
