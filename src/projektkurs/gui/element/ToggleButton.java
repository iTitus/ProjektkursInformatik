package projektkurs.gui.element;

import java.awt.event.MouseEvent;

public class ToggleButton extends Button {

	private int index;
	private String[] names;

	public ToggleButton(int posX, int posY, int sizeX, int sizeY, int id,
			IButtonListener gui, String... names) {
		super(posX, posY, sizeX, sizeY, id, gui, names[0]);
		this.names = names;
		index = 0;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			setIndex(index + 1);
			gui.onButtonLeftClick(this);
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			setIndex(index - 1);
			gui.onButtonRightClick(this);
		}
	}

	public void setIndex(int index) {
		this.index = index;
		if (this.index < 0)
			this.index = names.length - 1;
		if (this.index >= names.length)
			this.index = 0;
		name = names[this.index];
	}

}
