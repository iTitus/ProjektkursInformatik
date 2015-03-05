package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerryman extends EntityDialog {

    public EntityFerryman(Spielfeld map) {
        super(map);
    }

    public EntityFerryman(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.guy0);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
