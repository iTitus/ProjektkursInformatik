package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntitySchranke extends EntityDialog {
    public EntitySchranke(Spielfeld map) {
        super(map);
    }

    public EntitySchranke(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 2, Sprites.schranke);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }

}
