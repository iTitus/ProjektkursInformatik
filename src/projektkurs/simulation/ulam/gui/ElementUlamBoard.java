package projektkurs.simulation.ulam.gui;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.ulam.UlamBoard;
import projektkurs.util.RenderUtil;

public class ElementUlamBoard extends Element {

    public static final int SIZE = 1;
    private final UlamBoard board;

    public ElementUlamBoard(int posX, int posY, int size, int id, boolean mode) {
        super(posX, posY, size * SIZE, size * SIZE, id, null);
        board = new UlamBoard(size, mode);
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
        Font.drawString(screen, "Progress: " + board.getProgress() + "%", posX, posY, 0xFF01FF);
    }

    @Override
    public void renderTooltip(Screen screen) {
        int mX = Main.getInputManager().getMouseX();
        int mY = Main.getInputManager().getMouseY();
        if (isInside(mX, mY)) {
            int count = board.getCount((mX - posX) / SIZE, (mY - posY) / SIZE);
            if (count > 0) {
                RenderUtil.drawTooltip(screen, "" + count, Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY());
            }
        }
    }

    public void start() {
        board.start();
    }

    public void stop() {
        board.stop();
    }

}
