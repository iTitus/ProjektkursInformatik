package projektkurs.simulation.ulam;

import java.util.concurrent.atomic.AtomicIntegerArray;

import projektkurs.render.Screen;
import projektkurs.simulation.ulam.gui.ElementUlamBoard;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class UlamBoard {

	private final int[][] count;
	private final PrimeSearchThread primeSearchThread;
	private final int size;
	private volatile AtomicIntegerArray board;
	private boolean first = true;

	public UlamBoard(int size, boolean mode) {
		this.size = size;
		board = new AtomicIntegerArray(size * size);
		count = new int[size][size];
		primeSearchThread = new PrimeSearchThread(this, mode);
	}

	public int getCount(int x, int y) {
		if (x < 0 || x >= size || y < 0 || y >= size) {
			return 0;
		}
		return count[x][y];
	}

	public int getProgress() {
		return primeSearchThread.getProgress();
	}

	public int getSize() {
		return size;
	}

	public boolean isPrime(int i) {
		return board.get(i) == 0;
	}

	public void render(Screen screen, int offsetX, int offsetY) {
		int x = MathUtil.floorDiv(size, 2);
		int y = x;
		Direction d = Direction.RIGHT;
		int count = 0;
		for (double i = 2; i < size; i += 0.5) {
			for (int j = 1; j < (int) i; j++) {
				if (isPrime(++count)) {
					RenderUtil.drawFilledRectangle(screen, offsetX + x * ElementUlamBoard.SIZE, offsetY + y * ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, 0xFFFFFF);
				} else {
					RenderUtil.drawFilledRectangle(screen, offsetX + x * ElementUlamBoard.SIZE, offsetY + y * ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, 0x000000);
				}
				if (first) {
					this.count[x][y] = count;
				}
				x += d.getOffsetX();
				y += d.getOffsetY();
			}
			d = d.rotate(true, false);
		}
		if (first) {
			first = false;
		}
		RenderUtil.drawFilledRectangle(screen, offsetX + MathUtil.floorDiv(size, 2) * ElementUlamBoard.SIZE, offsetY + MathUtil.floorDiv(size, 2) * ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, ElementUlamBoard.SIZE, 0);
	}

	public void setNoPrime(int i) {
		board.lazySet(i, 1);
	}

	public void setPrime(int i) {
		board.lazySet(i, 0);
	}

	public void start() {
		primeSearchThread.start();
	}

	public void stop() {
		primeSearchThread.requestStop();
	}

}
