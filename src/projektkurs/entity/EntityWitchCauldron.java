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
        if (1 << 8 == (map.getLevel().getDialogManager().getValue() & 1 << 8)) {
            return Dialoge.LVmJungeAmWegesrandTwo;
        } else if ((1 << 5 | 1 << 6 | 1 << 7) == (map.getLevel().getDialogManager().getValue() & (1 << 5 | 1 << 6 | 1 << 7))) {
            return Dialoge.LVmHexerzirkelOne;
        } else {
            return Dialoge.LVmHexerzirkel;
        }

    }

}
