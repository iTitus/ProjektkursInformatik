package projektkurs.simulation.mandelbrot;

import java.util.concurrent.atomic.AtomicInteger;

public class MandelbrotIteratorThread extends Thread {

	private final BoardMandelbrotSet board;
	private final AtomicInteger iteration;
	private double posX = 0, posY = 0;
	private volatile boolean stopRequested = false;
	private double zoom = 1;

	public MandelbrotIteratorThread(BoardMandelbrotSet board) {
		super("Mandelbrot Set Iterator");
		this.board = board;
		iteration = new AtomicInteger();
	}

	public void decreaseZoom(double by) {
		zoom /= by;
	}

	public int getIteration() {
		return iteration.get();
	}

	public void setIteration(int i) {
		iteration.lazySet(i);
	}

	public double getMaxX() {
		return board.isBrot() ? 1.5 / zoom : 2.25 / zoom;
	}

	public double getMaxY() {
		return 1.25 / zoom;
	}

	public double getMinX() {
		return board.isBrot() ? 3 / zoom : 2.25 / zoom;
	}

	public double getMinY() {
		return 1.25 / zoom;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public void increaseZoom(double by) {
		zoom *= by;
	}

	public void move(double dX, double dY) {
		posX += dX;
		posY += dY;
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
				double i = y * (getMinY() + getMaxY() - posY) / board.getSizeY() - getMinY() - posY;
				for (int x = 0; x < board.getSizeX(); x++) {
					double r = x * (getMinX() + getMaxX() - posX) / board.getSizeX() - getMinX() - posX;
					if (stopRequested) {
						return;
					}
					board.set(x, y, isInMandelbrotSet(r, i, board.getC_r(), board.getC_i()));
				}
			}
		}
	}

	public void setPos(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	private int isInMandelbrotSet(double x, double y, double r, double i) {
		double x_r = board.isBrot() ? 0 : x, x_i = board.isBrot() ? 0 : y;
		int max = iteration.get();
		for (int n = 0; n < max; n++) {
			double temp = x_r * x_r - x_i * x_i + (board.isBrot() ? x : r);
			x_i = 2 * x_r * x_i + (board.isBrot() ? y : i);
			x_r = temp;
			if (x_r * x_r + x_i * x_i >= (board.isBrot() ? 4 : 0xB09A)) {
				return n;
			}
		}
		return max;
	}
}
