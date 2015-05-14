package projektkurs.simulation.life.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.lib.Integers;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.life.GameOfLifeBoard;
import projektkurs.util.RenderUtil;

public class ElementGameOfLife extends Element {

	public static final int SIZE = 8;
	private final GameOfLifeBoard lifeBoard;
	private boolean grid = true;
	private double speed = 10;

	public ElementGameOfLife(int posX, int posY, int sizeX, int sizeY, int id) {
		super(posX, posY, sizeX * SIZE, sizeY * SIZE, id, null);
		lifeBoard = new GameOfLifeBoard(sizeX, sizeY);
	}

	@Override
	public boolean canUpdate() {
		int i = (int) (Integers.UPS / speed);
		return lifeBoard.canUpdate() && (i <= 0 || Main.getTicks() % i == 0);
	}

	@Override
	public void onKeyTyped(char keyChar, KeyEvent e) {
		if (keyChar == ' ') {
			lifeBoard.setCanUpdate(!lifeBoard.canUpdate());
		}
		if (keyChar == 'r') {
			lifeBoard.reset();
		}
		if (keyChar == 'g') {
			grid = !grid;
		}
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		int boardX = (x - posX) / SIZE;
		int boardY = (y - posY) / SIZE;
		if (boardX >= 0 && boardY >= 0 && boardX < lifeBoard.getSizeX() && boardY < lifeBoard.getSizeY()) {
			lifeBoard.setBoard(boardX, boardY, true);
		}
	}

	@Override
	public void onMouseWheelMoved(int by, MouseWheelEvent e) {
		if (by < 0) {
			if (speed > 1) {
				speed--;
			}
		} else if (by > 0) {
			if (speed < Integers.UPS) {
				speed++;
			}
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		int boardX = (x - posX) / SIZE;
		int boardY = (y - posY) / SIZE;
		if (boardX >= 0 && boardY >= 0 && boardX < lifeBoard.getSizeX() && boardY < lifeBoard.getSizeY()) {
			lifeBoard.setBoard(boardX, boardY, false);
		}
	}

	@Override
	public void render(Screen screen) {
		if (grid) {
			for (int x = 0; x < sizeX / SIZE; x++) {
				RenderUtil.drawLine(screen, x * SIZE + posX, posY, x * SIZE + posX, posY + sizeY, 0xDDDDDD);
			}
			for (int y = 0; y < sizeY / SIZE; y++) {
				RenderUtil.drawLine(screen, posX, y * SIZE + posY, posX + sizeX, y * SIZE + posY, 0xDDDDDD);
			}
		}
		lifeBoard.render(screen, posX, posY);
		RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
		if (isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
			RenderUtil.drawRectangle(screen, SIZE * ((Main.getInputManager().getMouseX() - posX) / SIZE) + posX, SIZE * ((Main.getInputManager().getMouseY() - posY) / SIZE) + posY, SIZE, SIZE);
		}
		Font.drawString(screen, "Generation: " + lifeBoard.getGeneration() + ", Life: " + lifeBoard.getLife() + ", Speed: " + speed, posX, posY);
	}

	@Override
	public void update() {
		lifeBoard.update();
	}
}
