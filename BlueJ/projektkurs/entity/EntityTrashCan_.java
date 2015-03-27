package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityTrashCan_ extends EntityDialog {
    public EntityTrashCan_(Spielfeld map) {
        super(map);
    }

    public EntityTrashCan_(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 2, Sprites.trashcan);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }
}
