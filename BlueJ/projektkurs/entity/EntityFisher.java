package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogManager;
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
        if ((1 | 2 | 4) == (DialogManager.getValue() & 1 | 2 | 4)) {
            return Dialoge.LVmFischer;
        }
        return Dialoge.LVmFischerOne;
    }
}
