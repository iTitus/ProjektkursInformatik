package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityVilleCar extends EntityDialog {

    public EntityVilleCar(Spielfeld map) {
        super(map);
    }

    public EntityVilleCar(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 4, Sprites.car_frauV);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
