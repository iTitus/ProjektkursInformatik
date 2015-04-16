package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogManager;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerryman extends EntityDialog {

    public EntityFerryman(Spielfeld map) {
        super(map);
    }

    public EntityFerryman(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.boy);
    }

    @Override
    public Dialog getDialog() {
        if (1 << 18 == (DialogManager.getValue() & 1 << 18)) {
            return Dialoge.LVmFaehrmannTwo;
        } else if (1 == (DialogManager.getValue() & 1)) {
            return Dialoge.LVmFaehrmannOne;
        }
        return Dialoge.LVmFaehrmann;
    }
}
