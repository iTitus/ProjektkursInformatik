package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityNest extends EntityDialog {

    public EntityNest(Spielfeld map) {
        super(map);
    }

    public EntityNest(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.nest);
    }

    @Override
    public Dialog getDialog() {
        if (1 << 16 == (map.getLevel().getDialogManager().getValue() & 1 << 16)) {
            return Dialoge.LVmNestOne;
        }
        return Dialoge.LVmNest;
    }
}
