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
        if ((0 | 1 << 1 | 1 << 2) == (map.getLevel().getDialogManager().getValue() & (0 | 1 << 1 | 1 << 2))) {
            return Dialoge.LVmFrau;
        } else if ((1 << 1 | 1 << 4) == (map.getLevel().getDialogManager().getValue() & (1 << 1 | 1 << 4))) {
            return Dialoge.LVmFrauOne;
        } else if ((1 << 3 | 1 << 4) == (map.getLevel().getDialogManager().getValue() & (1 << 3 | 1 << 4))) {
            return Dialoge.LVmFrauTwo;
        } else if ((1 << 5 | 1 << 7 | 1 << 8) == (map.getLevel().getDialogManager().getValue() & (1 << 5 | 1 << 7 | 1 << 8))) {
            return Dialoge.LVmFrauThree;
        } else {
            return Dialoge.LVmFischerStd;
        }

    }

}
