package projektkurs.simulation.mandelbrot;

@SuppressWarnings("unused")
public class MandelbrotIteratorThread extends Thread {

    private static final int MAX_ITERATIONS = 32;
    private final BoardMandelbrotSet board;
    private final double incrementX, incrementY;
    private volatile int iteration;
    private volatile boolean stopRequested = false;
    private final Complex[][] values;

    public MandelbrotIteratorThread(BoardMandelbrotSet board) {
        super("Mandelbrot Set Iterator");
        this.board = board;
        values = new Complex[board.getSizeX()][board.getSizeY()];
        incrementX = board.getSizeX() / board.getX();
        incrementY = board.getSizeY() / board.getY();
    }

    public int getIteration() {
        return iteration;
    }

    public void requestStop() {
        stopRequested = true;
    }

    @Override
    public void run() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            iteration = i;
            for (int y = 0; y < board.getSizeY(); y++) {
                for (int x = 0; x < board.getSizeX(); x++) {
                    if (stopRequested) {
                        return;
                        // values[x][y] = values[x][y].square().add(new Complex(x * incrementX, y * incrementY));
                        // board.set(x, y, );
                    }
                }
            }
        }
    }

}
