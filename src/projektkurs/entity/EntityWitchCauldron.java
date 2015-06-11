package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityWitchCauldron extends EntityDialog {

    public EntityWitchCauldron(Spielfeld map) {
        super(map);
    }

    public EntityWitchCauldron(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 4, Sprites.witchCauldron);
    }

    @Override
    public Dialog getDialog() {

        if (1 << 18 == (map.getLevel().getDialogManager().getValue() & 1 << 18)) {
            return Dialoge.LVmHexerzirkelStd;
        } else if (1 << 17 == (map.getLevel().getDialogManager().getValue() & 1 << 17)) {
            return Dialoge.LVmHexerzirkelTwo;
        } else if ((1 << 12 | 1 << 13 | 1 << 14) == (map.getLevel().getDialogManager().getValue() & (1 << 12 | 1 << 13 | 1 << 14))) {
            return Dialoge.LVmHexerzirkelOne;
        } else if ((1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11) == (map.getLevel().getDialogManager().getValue() & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11))) {
            return Dialoge.LVmHexerzirkelNull;
        } else {
            return Dialoge.LVmHexerzirkelNull;
        }

    }

}
