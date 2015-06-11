package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityBoy extends EntityDialog {
    public EntityBoy(Spielfeld map) {
        super(map);
    }

    public EntityBoy(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.boy);
    }

    @Override
    public Dialog getDialog() {
        if (1 << 16 == (map.getLevel().getDialogManager().getValue() & 1 << 16)) {
            return Dialoge.LVmJungeAmWegesrandStd;
        } else if ((1 << 12 | 1 << 13 | 1 << 14) == (map.getLevel().getDialogManager().getValue() & (1 << 12 | 1 << 13 | 1 << 14))) {
            return Dialoge.LVmJungeAmWegesrandOne;
        } else if (0 == (map.getLevel().getDialogManager().getValue() & 0)) {
            return Dialoge.LVmJungeAmWegesrandNull;
        } else {
            return Dialoge.LVmJungeAmWegesrandNull;
        }
    }

}
