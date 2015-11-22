package projektkurs.simulation.tictactoe;

import projektkurs.util.MathUtil;

public class AI {

    private class AIThread extends Thread {

        volatile boolean finished = false, stop = false;
        volatile int x, y;

        @Override
        public void run() {
            int x, y;
            do {
                if (stop) {
                    return;
                }
                x = MathUtil.randomInt(0, TicTacToeBoard.SIZE);
                y = MathUtil.randomInt(0, TicTacToeBoard.SIZE);
            } while (board.getSign(x, y) != null);
            this.x = x;
            this.y = y;
            finished = true;

        }
    }

    private final TicTacToeBoard board;

    private AIThread calcThread;

    public AI(TicTacToeBoard board) {
        this.board = board;
    }

    public TicTacToeBoard getBoard() {
        return board;
    }

    public boolean update() {
        if (calcThread == null) {
            calcThread = new AIThread();
            calcThread.start();
            return false;
        }
        if (calcThread.finished) {
            board.setSign(calcThread.x, calcThread.y, EnumBoardSign.CIRCLE);
            calcThread.stop = true;
            calcThread = null;
            return true;
        }
        return false;
    }

}
