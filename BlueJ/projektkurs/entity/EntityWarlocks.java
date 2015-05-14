package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityWarlocks extends EntityDialog {

	public EntityWarlocks(Spielfeld map) {
		super(map);
	}

	public EntityWarlocks(Spielfeld map, int posX, int posY) {
		super(map, posX, posY, 1, 1, Sprites.wall);// Sprites.)
	}

	@Override
	public Dialog getDialog() {
		// TODO Auto-generated method stub
		return null;
	}
}
