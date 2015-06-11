package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityNest extends EntityDialog {

    public EntityNest(Spielfeld map) {
        super(map);
    }

    public EntityNest(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.nest);
    }

    @Override
    public Dialog getDialog() {
        if (1 << 17 == (map.getLevel().getDialogManager().getValue() & 1 << 17)) {
            return Dialoge.LVmNestStd;
        } else if ((1 << 15 | 1 << 16) == (map.getLevel().getDialogManager().getValue() & (1 << 15 | 1 << 16))) {
            return Dialoge.LVmNestOne;
        } else if ((1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11) == (map.getLevel().getDialogManager().getValue() & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11))) {
            return Dialoge.LVmNestNull;
        } else {
            return Dialoge.LVmNestNull;
        }
    }
}
