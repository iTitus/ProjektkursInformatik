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
        if (1 << 7 == (map.getLevel().getDialogManager().getValue() & 1 << 7)) {
            return Dialoge.LVmJungeAmWegesrandTwo;
        } else if ((1 << 5 | 1 << 6 | 1 << 8) == (map.getLevel().getDialogManager().getValue() & (1 << 5 | 1 << 6 | 1 << 8))) {
            return Dialoge.LVmJungeAmWegesrandOne;
        } else {
            return Dialoge.LVmJungeAmWegesrand;
        }

    }
}
