package projektkurs.simulation.tictactoe;

public class TicTacToeBoard {

    public static final int SIZE = 3;

    private final EnumBoardSign[] board;

    public TicTacToeBoard() {
        board = new EnumBoardSign[SIZE * SIZE];
    }

    public EnumBoardSign getSign(int x, int y) {
        return board[x + y * SIZE];
    }

    public void render() {
        // TODO
    }

    public void setSign(int x, int y, EnumBoardSign sign) {
        board[x + y * SIZE] = sign;
    }

}
