package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityJunge extends EntityDialog {
    public EntityJunge(Spielfeld map) {
        super(map);
    }

    public EntityJunge(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.junge);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
