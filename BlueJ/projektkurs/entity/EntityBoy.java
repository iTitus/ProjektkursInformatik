package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogManager;
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
        if ((1 << 13 | 1 << 12 | 1 << 14) == (DialogManager.getValue() & 1 << 13 | 1 << 12 | 1 << 14)) {
            return Dialoge.LVmJungeAmWegesrandOne;
        }
        return Dialoge.LVmJungeAmWegesrand;
    }
}
