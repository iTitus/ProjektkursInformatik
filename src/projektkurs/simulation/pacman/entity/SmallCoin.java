package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class SmallCoin extends PacmanEntity {

	public static final int POINTS = 5;

	public SmallCoin(PacmanBoard board, double x, double y) {
		super(board, x, y, 0.5, 0.5);
	}

	@Override
	public void onCollide(PacmanEntity e) {
		if (e instanceof Pacman) {
			board.increaseScore(POINTS);
			setDead();
		}
	}

	@Override
	public void render(Screen screen, int offsetX, int offsetY) {
		RenderUtil.drawFilledRectangle(screen, offsetX + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + MathUtil.floor(ElementPacmanBoard.SIZE * y), MathUtil.floor(sizeX * ElementPacmanBoard.SIZE), MathUtil.floor(sizeY * ElementPacmanBoard.SIZE), 0xFFFF00);
	}
}
