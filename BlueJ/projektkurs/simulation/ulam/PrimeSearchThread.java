package projektkurs.simulation.ulam;

import java.util.HashSet;

import projektkurs.util.MathUtil;

public class PrimeSearchThread extends Thread {

    private final UlamBoard board;
    private final double max;
    private final boolean mode;
    private final HashSet<Integer> primeSet = new HashSet<Integer>();
    private volatile int progress;
    private volatile boolean stopRequested = false;

    public PrimeSearchThread(UlamBoard board, boolean mode) {
        super("Prime Search Thread");
        this.board = board;
        this.mode = mode;
        max = board.getSize() * board.getSize();
        primeSet.add(2);
    }

    public int getProgress() {
        return progress;
    }

    public void requestStop() {
        stopRequested = true;
    }

    @Override
    public void run() {
        if (mode) {
            for (int i = 0; !stopRequested && i < max; i++) {
                if (!isPrime(i)) {
                    board.setNoPrime(i);
                }
                progress = MathUtil.round(i * 100 / max);
            }
        } else {
            for (int i = 2; !stopRequested && i < max; i++) {
                for (int j = 2; !stopRequested && j <= max / i - 1; j++) {
                    // Busyloop
                    for (int k = 0; !stopRequested && k < 100; k++) {
                        Math.sin(k);
                    }
                    board.setNoPrime(j * i);
                }
                if (!stopRequested && i + 1 < board.getSize() * board.getSize() && !board.isPrime(i + 1)) {
                    int j = i + 1;
                    while (!stopRequested && j + 1 < board.getSize() * board.getSize() && !board.isPrime(j + 1)) {
                        j++;
                    }
                    i = j;
                }
                progress = MathUtil.round(i * 100 / max);
            }
        }
    }

    private boolean isPrime(int i) {
        if (i <= 1) {
            return false;
        } else if (i == 2) {
            return true;
        } else {
            for (int prime : primeSet) {
                int k = i / prime;
                double d = (double) i / prime;
                if (Math.abs(k - d) <= Double.MIN_NORMAL) {
                    return false;
                }
            }
            primeSet.add(i);
            return true;
        }
    }
}
