package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFisher extends EntityDialog {

    public EntityFisher(Spielfeld map) {
        super(map);
    }

    public EntityFisher(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.fisher);
    }

    @Override
    public Dialog getDialog() {
        if ((1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11) == (map.getLevel().getDialogManager().getValue() & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11))) {
            return Dialoge.LVmFischerStd;
        } else if ((1 << 2 | 1 << 3 | 1 << 4 | 1 << 5) == (map.getLevel().getDialogManager().getValue() & (1 << 2 | 1 << 3 | 1 << 4 | 1 << 5))) {
            return Dialoge.LVmFischerNull;
        } else {
            return Dialoge.LVmFischerNull;
        }
    }
}
