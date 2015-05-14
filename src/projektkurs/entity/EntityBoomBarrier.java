package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityBoomBarrier extends EntityDialog {
	public EntityBoomBarrier(Spielfeld map) {
		super(map);
	}

	public EntityBoomBarrier(Spielfeld map, int posX, int posY) {
		super(map, posX, posY, 4, 2, Sprites.boomBarrier);
	}

	@Override
	public Dialog getDialog() {
		return Dialoge.LVmSchranke;
	}

}
