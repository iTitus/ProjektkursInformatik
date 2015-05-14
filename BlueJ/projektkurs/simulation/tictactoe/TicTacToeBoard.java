package projektkurs.simulation.tictactoe;

import projektkurs.render.Screen;
import projektkurs.simulation.tictactoe.gui.ElementTicTacToe;
import projektkurs.util.Direction;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class TicTacToeBoard implements IUpdatable {

	public static final int SIZE = 3;

	private AI ai;
	private EnumBoardSign[] board;
	private Direction finishDirection;
	private int finishStartX, finishStartY;
	private boolean player, finished;

	public TicTacToeBoard() {
		board = new EnumBoardSign[SIZE * SIZE];
		player = MathUtil.randomBoolean();
		ai = new AI(this);
		finished = false;
		finishStartX = -1;
		finishStartY = -1;
		finishDirection = Direction.UNKNOWN;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public EnumBoardSign getSign(int x, int y) {
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			return null;
		}
		return board[x + y * SIZE];
	}

	public boolean isFinished() {
		return finished;
	}

	public void onLeftClick(int x, int y) {
		if (!finished && player && getSign(x, y) == null) {
			setSign(x, y, EnumBoardSign.CROSS);
			player = false;
		}
	}

	public void render(Screen screen, int offsetX, int offsetY) {
		if (finished) {
			for (int i = 0; i < 3; i++) {
				int x = finishStartX + i * finishDirection.getOffsetX();
				int y = finishStartY + i * finishDirection.getOffsetY();
				RenderUtil.drawFilledRectangle(screen, offsetX + x * ElementTicTacToe.SIZE, offsetY + y * ElementTicTacToe.SIZE, ElementTicTacToe.SIZE, ElementTicTacToe.SIZE, 0xFF0000);
			}
		}
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				EnumBoardSign sign = getSign(x, y);
				if (sign != null) {
					sign.render(screen, this, x, y, offsetX, offsetY);
				}
			}
		}
	}

	public void reset() {
		board = new EnumBoardSign[SIZE * SIZE];
		player = MathUtil.randomBoolean();
		ai = new AI(this);
		finished = false;
		finishStartX = -1;
		finishStartY = -1;
		finishDirection = Direction.UNKNOWN;
	}

	public void setSign(int x, int y, EnumBoardSign sign) {
		if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
			board[x + y * SIZE] = sign;
		}
	}

	@Override
	public void update() {
		if (!finished) {
			if (!player && ai.update()) {
				player = true;
			}
		}

		if (!finished) {
			for (EnumBoardSign sign : EnumBoardSign.values()) {
				check(0, 0, sign, Direction.DOWN_RIGHT);
				for (int x = 0; x < SIZE; x++) {
					check(x, 0, sign, Direction.DOWN);
				}
				for (int y = 0; y < SIZE; y++) {
					check(0, y, sign, Direction.RIGHT);
				}
				check(0, SIZE - 1, sign, Direction.UP_RIGHT);
			}
		}
	}

	private void check(int x, int y, EnumBoardSign sign, Direction d) {
		for (int i = 0; i < SIZE; i++) {
			if (getSign(x + i * d.getOffsetX(), y + i * d.getOffsetY()) != sign) {
				return;
			}
		}
		finished = true;
		finishStartX = x;
		finishStartY = y;
		finishDirection = d;
	}
}
