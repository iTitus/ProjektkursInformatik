package projektkurs.gui.element;

import projektkurs.Main;

public class ToggleButton extends Button {

	private int index;
	private String[] names;

	public ToggleButton(int posX, int posY, int sizeX, int sizeY, int id,
			String... names) {
		super(posX, posY, sizeX, sizeY, id, names[0]);
		this.names = names;
		index = 0;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void onLeftClick(int x, int y) {
		if (isInside(x, y)) {
			index++;
			if (index >= names.length)
				index = 0;
			name = names[index];
			Main.getGui().onButtonLeftClick(this);
		}
	}

	@Override
	public void onRightClick(int x, int y) {
		if (isInside(x, y)) {
			index--;
			if (index < 0)
				index = names.length - 1;
			name = names[index];
			Main.getGui().onButtonRightClick(this);
		}
	}

	public void setIndex(int index) {
		this.index = index;
		if (this.index < 0)
			this.index = 0;
		if (this.index >= names.length)
			this.index = names.length - 1;
		name = names[this.index];
	}

}
