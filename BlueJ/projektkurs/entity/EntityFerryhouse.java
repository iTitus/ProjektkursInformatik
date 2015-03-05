package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerryhouse extends EntityDialog {

    public EntityFerryhouse(Spielfeld map) {
        super(map);
    }

    public EntityFerryhouse(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 7, Sprites.ferryhouse);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
