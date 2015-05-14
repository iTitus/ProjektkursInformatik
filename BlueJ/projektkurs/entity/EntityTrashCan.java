package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityTrashCan extends EntityDialog {
	public EntityTrashCan(Spielfeld map) {
		super(map);
	}

	public EntityTrashCan(Spielfeld map, int posX, int posY) {
		super(map, posX, posY, 2, 2, Sprites.trashCan);
	}

	@Override
	public Dialog getDialog() {
		return Dialoge.LVmTrashCan;
	}
}
