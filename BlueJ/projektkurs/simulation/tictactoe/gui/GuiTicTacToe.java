package projektkurs.simulation.tictactoe.gui;

import projektkurs.gui.Gui;
import projektkurs.lib.Integers;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiTicTacToe extends Gui {

	private ElementTicTacToe elementTicTacToe;

	@Override
	public void initGui() {
		super.initGui();
		elementTicTacToe = new ElementTicTacToe(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 0);
		addElement(elementTicTacToe);
	}

	@Override
	public void render(Screen screen) {
		RenderUtil.drawDefaultBackground(screen);
		super.render(screen);
	}

}
