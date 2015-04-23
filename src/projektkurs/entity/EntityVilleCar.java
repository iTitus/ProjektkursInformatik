package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogManager;
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

        if (1 << 19 == (DialogManager.getValue() & 1 << 19)) {
            return Dialoge.LVmFrauThree;
        } else if ((1 << 12 | 1 << 13 | 1 << 14) == (DialogManager.getValue() & 1 << 12 | 1 << 13 | 1 << 14)) {
            return Dialoge.LVmFrauTwo;
        } else if ((0b100 | 0b1000 | 1 << 4 | 1 << 5) == (DialogManager.getValue() & 0b100 | 0b1000 | 1 << 4 | 1 << 5)) {
            return Dialoge.LVmFrauOne;
        }
        return Dialoge.LVmFrau;

    }

}
