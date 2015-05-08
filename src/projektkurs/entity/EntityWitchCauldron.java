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
        if ((1 << 15 | 1 << 16 | 1 << 17) == (1 << 15 | 1 << 16 | 1 << 17 & map.getLevel().getDialogManager().getValue())) {
            return Dialoge.LVmHexerzirkelTwo;
        } else if ((1 << 12 | 1 << 13 | 1 << 14) == (1 << 12 | 1 << 13 | 1 << 14 & map.getLevel().getDialogManager().getValue())) {
            return Dialoge.LVmHexerzirkelOne;
        }
        return Dialoge.LVmHexerzirkel;

    }

}
