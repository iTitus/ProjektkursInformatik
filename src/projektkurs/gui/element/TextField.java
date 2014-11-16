package projektkurs.gui.element;

import java.awt.Graphics2D;

public class TextField extends Element {

	protected ITextFieldListener gui;

	public TextField(int posX, int posY, int sizeX, int sizeY, int id,
			ITextFieldListener gui) {
		super(posX, posY, sizeX, sizeY, id);
		this.gui = gui;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
