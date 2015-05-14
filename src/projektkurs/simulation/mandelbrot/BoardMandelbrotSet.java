package projektkurs.simulation.mandelbrot;

import java.util.concurrent.atomic.AtomicIntegerArray;

import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class BoardMandelbrotSet {

	private final AtomicIntegerArray board;
	private final boolean brot;
	private final double c_r, c_i;
	private final MandelbrotIteratorThread mandelbrotIteratorThread;
	private final int sizeX, sizeY;

	public BoardMandelbrotSet(int sizeX, int sizeY, double c_r, double c_i, boolean brot) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		mandelbrotIteratorThread = new MandelbrotIteratorThread(this);
		board = new AtomicIntegerArray(sizeX * sizeY);
		this.c_r = c_r;
		this.c_i = c_i;
		this.brot = brot;
	}

	public void decreaseZoom(double by) {
		mandelbrotIteratorThread.decreaseZoom(by);
	}

	public int get(int x, int y) {
		return board.get(x + y * sizeX);
	}

	public double getC_i() {
		return c_i;
	}

	public double getC_r() {
		return c_r;
	}

	public int getIteration() {
		return mandelbrotIteratorThread.getIteration();
	}

	public void setIteration(int i) {
		mandelbrotIteratorThread.setIteration(i);
	}

	public double getMaxX() {
		return mandelbrotIteratorThread.getMaxX();
	}

	public double getMaxY() {
		return mandelbrotIteratorThread.getMaxY();
	}

	public double getMinX() {
		return mandelbrotIteratorThread.getMinX();
	}

	public double getMinY() {
		return mandelbrotIteratorThread.getMinY();
	}

	public double getPosX() {
		return mandelbrotIteratorThread.getPosX();
	}

	public void setPosX(double posX) {
		mandelbrotIteratorThread.setPosX(posX);
	}

	public double getPosY() {
		return mandelbrotIteratorThread.getPosY();
	}

	public void setPosY(double posY) {
		mandelbrotIteratorThread.setPosY(posY);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public double getZoom() {
		return mandelbrotIteratorThread.getZoom();
	}

	public void setZoom(double zoom) {
		mandelbrotIteratorThread.setZoom(zoom);
	}

	public void increaseZoom(double by) {
		mandelbrotIteratorThread.increaseZoom(by);
	}

	public boolean isBrot() {
		return brot;
	}

	public void move(double dX, double dY) {
		mandelbrotIteratorThread.move(dX, dY);
	}

	public void render(Screen screen, int offsetX, int offsetY) {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				int i = get(x, y);
				screen.setPixel(RenderUtil.getColor(i, i, 0), x + offsetX, y + offsetY);
			}
		}
		int centerX = (int) (MathUtil.abs(mandelbrotIteratorThread.getMinX()) / (MathUtil.abs(mandelbrotIteratorThread.getMinX()) + MathUtil.abs(mandelbrotIteratorThread.getMaxX())) * sizeX);
		int centerY = (int) (MathUtil.abs(mandelbrotIteratorThread.getMaxY()) / (MathUtil.abs(mandelbrotIteratorThread.getMinY()) + MathUtil.abs(mandelbrotIteratorThread.getMaxY())) * sizeY);
		RenderUtil.drawLine(screen, offsetX + centerX, offsetY, offsetX + centerX, offsetY + sizeY, 0xFF0000);
		RenderUtil.drawLine(screen, offsetX, offsetY + centerY, offsetX + sizeX, offsetY + centerY, 0xFF0000);
		Font.drawString(screen, String.format("Iteration: %d | X: %.2f | Y: %.2f | Zoom: %.2f", mandelbrotIteratorThread.getIteration(), mandelbrotIteratorThread.getPosX(), mandelbrotIteratorThread.getPosY(), mandelbrotIteratorThread.getZoom()), offsetX, offsetY, 0x0000DD);
	}

	public void set(int x, int y, int i) {
		board.lazySet(x + y * sizeX, i);
	}

	public void setPos(double posX, double posY) {
		mandelbrotIteratorThread.setPos(posX, posY);
	}

	public void start() {
		mandelbrotIteratorThread.start();
	}

	public void stop() {
		mandelbrotIteratorThread.requestStop();
	}

}
