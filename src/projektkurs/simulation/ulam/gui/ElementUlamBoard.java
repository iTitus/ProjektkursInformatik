package projektkurs.simulation.ulam.gui;

import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.gui.element.Element;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.ulam.UlamBoard;

public class ElementUlamBoard extends Element {

	public static final int SIZE = 1;
	private final UlamBoard board;

	public ElementUlamBoard(int posX, int posY, int size, int id, boolean mode) {
		super(posX, posY, size * SIZE, size * SIZE, id, null);
		board = new UlamBoard(size, mode);
	}

	@Override
	public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
		int count = board.getCount((mouseX - posX) / SIZE, (mouseY - posY) / SIZE);
		if (count > 0) {
			tooltip.add("" + count);
		}
	}

	@Override
	public void render(Screen screen) {
		board.render(screen, posX, posY);
		Font.drawString(screen, "Progress: " + board.getProgress() + "%", posX, posY, 0xFF01FF);
	}

	public void start() {
		board.start();
	}

	public void stop() {
		board.stop();
	}

}
