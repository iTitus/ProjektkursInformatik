package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityDoor extends EntityDialog {
    public EntityDoor(Spielfeld map) {
        super(map);
    }

    public EntityDoor(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.door_NS);
    }

    @Override
    public Dialog getDialog() {

        if (1 << 9 == (map.getLevel().getDialogManager().getValue() & 1 << 9)) {
            return Dialoge.LVmFaehrmannTwo;
        } else if ((1 << 1 | 1 << 4 | 1 << 5 | 1 << 6 | 1 << 7 | 1 << 8) == (map.getLevel().getDialogManager().getValue() & (1 << 1 | 1 << 4 | 1 << 5 | 1 << 6 | 1 << 7 | 1 << 8))) {
            return Dialoge.LVmFaehrmannOne;
        } else {
            return Dialoge.LVmFaehrmann;
        }
    }
}
