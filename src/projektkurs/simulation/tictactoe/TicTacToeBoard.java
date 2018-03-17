package projektkurs.simulation.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import projektkurs.render.Screen;
import projektkurs.simulation.tictactoe.gui.ElementTicTacToe;
import projektkurs.util.Direction;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.Logger.LogLevel;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class TicTacToeBoard implements IUpdatable {

    public static final int SIZE = 3;
    public static final Direction[][] WINNABLE_ROWS = { { Direction.UP_LEFT, Direction.UP, Direction.UP_RIGHT }, { Direction.LEFT, Direction.CENTER, Direction.RIGHT }, { Direction.DOWN_LEFT, Direction.DOWN, Direction.DOWN_RIGHT }, { Direction.UP_LEFT, Direction.LEFT, Direction.DOWN_LEFT },
            { Direction.UP, Direction.CENTER, Direction.DOWN }, { Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT }, { Direction.UP_LEFT, Direction.CENTER, Direction.DOWN_RIGHT }, { Direction.UP_RIGHT, Direction.CENTER, Direction.DOWN_LEFT } };

    private static final int DIFFICULTY = AI.DIFFICULTY_EXPERT;

    private final List<Direction> history;

    private AI ai;
    private EnumBoardSign[] board;
    private Direction[] finishRow;
    private int round, difficulty;
    private boolean player, finished;
    private EnumBoardSign playerSign;

    public TicTacToeBoard() {
        history = new ArrayList<>(9);
        board = new EnumBoardSign[SIZE * SIZE];
        player = MathUtil.nextBoolean();
        playerSign = player ? EnumBoardSign.CROSS : EnumBoardSign.CIRCLE;
        ai = new AI(this, player ? EnumBoardSign.CIRCLE : EnumBoardSign.CROSS);
        finished = false;
        round = 1;
        difficulty = DIFFICULTY;
        finishRow = null;
        Logger.log(LogLevel.INFO, "Reset");
        if (!player) {
            Logger.log(LogLevel.INFO, "AI move start, difficulty: " + difficulty);
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public EnumBoardSign getSign(Direction pos) {
        if (pos == null) {
            return null;
        }
        return getSign(pos.getOffsetX() + 1, pos.getOffsetY() + 1);
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

    public EnumBoardSign getPlayerSign() {
        return playerSign;
    }

    public int getRound() {
        return round;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Direction getMove(int round, EnumBoardSign sign) {
        int index = 2 * (round - 1) + (sign == EnumBoardSign.CROSS ? 0 : 1);
        if (index < 0 || index >= history.size()) {
            return null;
        }
        return history.get(index);
    }

    public void onLeftClick(int x, int y) {
        if (!finished && player && getSign(x, y) == null) {
            setSign(x, y, playerSign);
            if (playerSign == EnumBoardSign.CIRCLE) {
                round++;
            }
            player = false;
            Logger.log(LogLevel.INFO, "AI move start");
        }
    }

    public void render(Screen screen, int offsetX, int offsetY) {
        if (finished) {
            if (finishRow == null) {
                RenderUtil.drawFilledRectangle(screen, offsetX, offsetY, SIZE * ElementTicTacToe.SIZE, SIZE * ElementTicTacToe.SIZE, 0xFF0000);
            } else {
                for (Direction pos : finishRow) {
                    RenderUtil.drawFilledRectangle(screen, offsetX + (pos.getOffsetX() + 1) * ElementTicTacToe.SIZE, offsetY + (pos.getOffsetY() + 1) * ElementTicTacToe.SIZE, ElementTicTacToe.SIZE, ElementTicTacToe.SIZE, 0xFF0000);
                }
            }
        }
        for (Direction pos : Direction.VALUES) {
            EnumBoardSign sign = getSign(pos);
            if (sign != null) {
                sign.render(screen, this, pos.getOffsetX() + 1, pos.getOffsetY() + 1, offsetX, offsetY);
            }
        }
    }

    public void reset() {
        history.clear();
        board = new EnumBoardSign[SIZE * SIZE];
        player = MathUtil.nextBoolean();
        playerSign = player ? EnumBoardSign.CROSS : EnumBoardSign.CIRCLE;
        ai = new AI(this, player ? EnumBoardSign.CIRCLE : EnumBoardSign.CROSS);
        finished = false;
        round = 1;
        difficulty = DIFFICULTY;
        finishRow = null;
        Logger.log(LogLevel.INFO, "Reset");
        if (!player) {
            Logger.log(LogLevel.INFO, "AI move start, difficulty: " + difficulty);
        }
    }

    public void setSign(Direction pos, EnumBoardSign sign) {
        if (pos != null) {
            setSign(pos.getOffsetX() + 1, pos.getOffsetY() + 1, sign);
        }
    }

    public void setSign(int x, int y, EnumBoardSign sign) {
        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            int i = x + y * SIZE;
            if ((sign != null && board[i] != null) || (sign == playerSign && !player) || (sign != null && sign != playerSign && player)) {
                throw new RuntimeException(String.format("x=%d, y=%d, sign=%s, sign_on_board=%s, playerSign=%s, player_turn=%s", x, y, sign, board[i], playerSign, player));
            }
            board[i] = sign;
            history.add(Direction.getDirectionForOffset(x - 1, y - 1));
        }
    }

    @Override
    public void update() {
        if (!finished) {
            loop: for (EnumBoardSign sign : EnumBoardSign.VALUES) {
                for (Direction[] row : WINNABLE_ROWS) {
                    if (checkWin(sign, row)) {
                        finished = true;
                        finishRow = row;
                        break loop;
                    }
                }
            }
        }

        if (!finished) {
            if (checkDraw()) {
                finished = true;
            }
        }

        if (!finished) {
            if (!player && ai.update()) {
                if (playerSign == EnumBoardSign.CROSS) {
                    round++;
                }
                player = true;
                Logger.log(LogLevel.INFO, "AI move finish");
            }
        }
    }

    public boolean checkPossibleWin(EnumBoardSign sign, Direction[] row) {
        return simulatePossibleWin(sign, row, Collections.<Direction, EnumBoardSign>emptyMap());
    }

    public boolean simulatePossibleWin(EnumBoardSign sign, Direction[] row, Map<Direction, EnumBoardSign> overrides) {
        boolean free = false;
        for (Direction pos : row) {
            EnumBoardSign sign_ = overrides.getOrDefault(pos, getSign(pos));
            if (sign_ == null) {
                if (free) {
                    return false;
                }
                free = true;
            } else if (sign_ != sign) {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(EnumBoardSign sign, Direction[] row) {
        for (Direction pos : row) {
            if (getSign(pos) != sign) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDraw() {
        for (Direction pos : Direction.VALUES) {
            if (getSign(pos) == null) {
                return false;
            }
        }
        return true;
    }

}
