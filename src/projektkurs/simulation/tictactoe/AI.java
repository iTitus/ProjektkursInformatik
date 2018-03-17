package projektkurs.simulation.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import projektkurs.util.Direction;
import projektkurs.util.Logger;
import projektkurs.util.Logger.LogLevel;
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;

public class AI {

    /**
     * Random moves
     */
    public static final int DIFFICULTY_NOVICE = 0;
    /**
     * Reactionary play
     */
    public static final int DIFFICULTY_INTERMEDIATE = 1;
    /**
     * Knows how to start
     */
    public static final int DIFFICULTY_EXPERIENCED = 2;
    /**
     * Never loses
     */
    public static final int DIFFICULTY_EXPERT = 3;

    private class AIThread extends Thread {

        volatile boolean finished = false;
        volatile int x, y;

        public AIThread() {
            super("Tic-Tac-Toe AI");
        }

        @Override
        public void run() {
            Direction pos = null;

            if (board.getDifficulty() > DIFFICULTY_NOVICE) {
                pos = tryToWin();
            }
            if (pos == null) {
                if (board.getDifficulty() > DIFFICULTY_NOVICE) {
                    pos = tryToBlock();
                }
                if (pos == null) {
                    if (board.getDifficulty() > DIFFICULTY_EXPERIENCED) {
                        pos = tryToFork();
                    }
                    if (pos == null) {
                        if (board.getDifficulty() > DIFFICULTY_EXPERIENCED) {
                            pos = tryToBlockFork();
                        }
                        if (pos == null) {
                            if (board.getDifficulty() > DIFFICULTY_INTERMEDIATE) {
                                if (board.getRound() == 1 && (sign == EnumBoardSign.CROSS || board.getMove(1, board.getPlayerSign()) == Direction.CENTER)) {
                                    pos = tryCorner();
                                } else {
                                    pos = tryCenter();
                                }
                            }
                            if (pos == null) {
                                if (board.getDifficulty() > DIFFICULTY_EXPERIENCED) {
                                    pos = tryOppositeCorner();
                                }
                                if (pos == null) {
                                    if (board.getDifficulty() > DIFFICULTY_EXPERIENCED) {
                                        pos = tryCorner();
                                    }
                                    if (pos == null) {
                                        if (board.getDifficulty() > DIFFICULTY_EXPERIENCED) {
                                            pos = tryEdge();
                                        }
                                        if (pos == null) {
                                            pos = chooseFree(Direction.VALUES);
                                            Logger.log(LogLevel.INFO, "random: " + pos);
                                        } else {
                                            Logger.log(LogLevel.INFO, "tryEdge: " + pos);
                                        }
                                    } else {
                                        Logger.log(LogLevel.INFO, "tryCorner: " + pos);
                                    }
                                } else {
                                    Logger.log(LogLevel.INFO, "tryOppositeCorner: " + pos);
                                }
                            } else {
                                Logger.log(LogLevel.INFO, "tryCenter/corner1st: " + pos);
                            }
                        } else {
                            Logger.log(LogLevel.INFO, "tryToBlockFork: " + pos);
                        }
                    } else {
                        Logger.log(LogLevel.INFO, "tryToFork: " + pos);
                    }
                } else {
                    Logger.log(LogLevel.INFO, "tryToBlock: " + pos);
                }
            } else {
                Logger.log(LogLevel.INFO, "tryToWin: " + pos);
            }

            this.x = pos.getOffsetX() + 1;
            this.y = pos.getOffsetY() + 1;
            finished = true;
        }

        private Direction tryEdge() {
            return chooseFree(Direction.EDGES);
        }

        private Direction tryCorner() {
            return chooseFree(Direction.CORNERS);
        }

        private Direction tryOppositeCorner() {
            List<Direction> opposingCorners = new ArrayList<>();
            for (Direction pos : Direction.CORNERS) {
                if (board.getSign(pos) == board.getPlayerSign()) {
                    opposingCorners.add(pos.getOpposite());
                }
            }
            if (!opposingCorners.isEmpty()) {
                return chooseFree(opposingCorners.toArray(new Direction[opposingCorners.size()]));
            }

            return null;
        }

        private Direction tryCenter() {
            return chooseFree(Direction.CENTER);
        }

        private Direction tryToBlockFork() {
            List<Direction> free = new ArrayList<>();
            for (Direction pos : Direction.VALUES) {
                if (board.getSign(pos) == null) {
                    free.add(pos);
                }
            }

            List<Direction> possiblePlayerForks = new ArrayList<>();
            for (Direction pos : free) {
                boolean fork = false;
                for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                    if (board.simulatePossibleWin(board.getPlayerSign(), row, getOverride(pos, board.getPlayerSign()))) {
                        if (fork) {
                            possiblePlayerForks.add(pos);
                            break;
                        }
                        fork = true;
                    }
                }
            }
            if (possiblePlayerForks.size() == 1) {
                return chooseFree(possiblePlayerForks.get(0));
            }
            for (Iterator<Direction> it = possiblePlayerForks.iterator(); it.hasNext();) {
                Direction pos = it.next();
                for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                    if (!board.simulatePossibleWin(sign, row, getOverride(pos, sign))) {
                        it.remove();
                        break;
                    }
                }

            }
            if (!possiblePlayerForks.isEmpty()) {
                return chooseFree(possiblePlayerForks.toArray(new Direction[possiblePlayerForks.size()]));
            }

            List<Direction> forceDefensePos = new ArrayList<>();
            for (Direction pos : free) {
                for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                    if (board.simulatePossibleWin(sign, row, getOverride(pos, sign))) {
                        boolean valid = true;

                        Direction blockingPos = chooseFree(getOverride(pos, board.getPlayerSign()), row);
                        if (blockingPos != null) {
                            boolean fork = false;
                            for (Direction[] row_ : TicTacToeBoard.WINNABLE_ROWS) {
                                if (board.simulatePossibleWin(board.getPlayerSign(), row_, getOverride(Pair.of(pos, sign), Pair.of(blockingPos, board.getPlayerSign())))) {
                                    if (fork) {
                                        valid = false;
                                        break;
                                    }
                                    fork = true;
                                }
                            }
                        }

                        if (valid) {
                            forceDefensePos.add(pos);
                        }

                    }
                }
            }
            if (!forceDefensePos.isEmpty()) {
                return chooseFree(forceDefensePos.toArray(new Direction[forceDefensePos.size()]));
            }

            return null;
        }

        private Direction tryToFork() {
            List<Direction> free = new ArrayList<>();
            for (Direction pos : Direction.VALUES) {
                if (board.getSign(pos) == null) {
                    free.add(pos);
                }
            }

            List<Direction> possibleForks = new ArrayList<>();
            for (Direction pos : free) {
                boolean fork = false;
                for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                    if (board.simulatePossibleWin(sign, row, getOverride(pos, sign))) {
                        if (fork) {
                            possibleForks.add(pos);
                            break;
                        }
                        fork = true;
                    }
                }
            }
            if (!possibleForks.isEmpty()) {
                return chooseFree(possibleForks.toArray(new Direction[possibleForks.size()]));
            }

            return null;
        }

        private Direction tryToBlock() {
            for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                if (board.checkPossibleWin(board.getPlayerSign(), row)) {
                    return chooseFree(row);
                }
            }
            return null;
        }

        private Direction tryToWin() {
            for (Direction[] row : TicTacToeBoard.WINNABLE_ROWS) {
                if (board.checkPossibleWin(sign, row)) {
                    return chooseFree(row);
                }
            }
            return null;
        }

    }

    private final TicTacToeBoard board;
    private final EnumBoardSign sign;

    private AIThread aiThread;

    public AI(TicTacToeBoard board, EnumBoardSign sign) {
        this.board = board;
        this.sign = sign;
    }

    public TicTacToeBoard getBoard() {
        return board;
    }

    public boolean update() {
        if (aiThread == null) {
            aiThread = new AIThread();
            aiThread.start();
            return false;
        }
        if (aiThread.finished) {
            board.setSign(aiThread.x, aiThread.y, sign);
            aiThread = null;
            return true;
        }
        return false;
    }

    private Direction chooseFree(Direction... directions) {
        return chooseFree(Collections.emptyMap(), directions);
    }

    private Direction chooseFree(Map<Direction, EnumBoardSign> overrides, Direction... directions) {
        Logger.log(LogLevel.INFO, "chooseFree(" + overrides + ", " + Arrays.toString(directions) + ")");
        if (directions == null || directions.length <= 0) {
            Logger.log(LogLevel.INFO, "\t-> null");
            return null;
        } else if (directions.length == 1) {
            Direction pos = directions[0];
            if (overrides.getOrDefault(pos, board.getSign(pos)) == null) {
                Logger.log(LogLevel.INFO, "\t-> " + pos);
                return pos;
            }
            Logger.log(LogLevel.INFO, "\t-> null");
            return null;
        }
        Direction[] validDirs = directions;
        Direction pos = null;
        do {
            if (pos != null) {
                List<Direction> list = new ArrayList<>();
                for (Direction dir_ : validDirs) {
                    if (dir_ != pos) {
                        list.add(dir_);
                    }
                }
                if (list.isEmpty()) {
                    Logger.log(LogLevel.INFO, "\t-> null");
                    return null;
                }
                validDirs = list.toArray(new Direction[list.size()]);
            }
            pos = MathUtil.chooseRandom(validDirs);
        } while (overrides.getOrDefault(pos, board.getSign(pos)) != null);
        Logger.log(LogLevel.INFO, "\t-> " + pos);
        return pos;
    }

    private Map<Direction, EnumBoardSign> getOverride(Direction pos, EnumBoardSign sign) {
        return Collections.singletonMap(pos, sign);
    }

    @SafeVarargs
    private final Map<Direction, EnumBoardSign> getOverride(Pair<Direction, EnumBoardSign>... pairs) {
        Map<Direction, EnumBoardSign> map = new HashMap<>();
        for (Pair<Direction, EnumBoardSign> pair : pairs) {
            map.put(pair.getValueA(), pair.getValueB());
        }
        return map;
    }

}
