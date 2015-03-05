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
        super(map, posX, posY, 1, 1, Sprites.guy1);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
