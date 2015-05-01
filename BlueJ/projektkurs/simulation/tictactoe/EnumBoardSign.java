package projektkurs.simulation.tictactoe;

import projektkurs.render.Screen;
import projektkurs.simulation.tictactoe.gui.ElementTicTacToe;
import projektkurs.util.RenderUtil;

public enum EnumBoardSign {

    CIRCLE {
        @Override
        public void render(Screen screen, TicTacToeBoard board, int x, int y, int offsetX, int offsetY) {
            RenderUtil.drawRectangle(screen, 4 + offsetX + x * ElementTicTacToe.SIZE, 4 + offsetY + y * ElementTicTacToe.SIZE, ElementTicTacToe.SIZE - 8, ElementTicTacToe.SIZE - 8);
        }
    },
    CROSS {
        @Override
        public void render(Screen screen, TicTacToeBoard board, int x, int y, int offsetX, int offsetY) {
            RenderUtil.drawLine(screen, offsetX + x * ElementTicTacToe.SIZE, offsetY + y * ElementTicTacToe.SIZE, offsetX + (x + 1) * ElementTicTacToe.SIZE, offsetY + (y + 1) * ElementTicTacToe.SIZE);
            RenderUtil.drawLine(screen, offsetX + (x + 1) * ElementTicTacToe.SIZE, offsetY + (y + 1) * ElementTicTacToe.SIZE, offsetX + x * ElementTicTacToe.SIZE, offsetY + (y + 2) * ElementTicTacToe.SIZE);
        }
    };

    public abstract void render(Screen screen, TicTacToeBoard board, int x, int y, int offsetX, int offsetY);

}
