package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerry extends EntityDialog {
    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityFerry(Spielfeld map) {
        super(map);
    }

    public EntityFerry(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 8, Sprites.ferry);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }

}
