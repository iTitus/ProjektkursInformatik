package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFisherboat extends EntityDialog {

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityFisherboat(Spielfeld map) {
        super(map);
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     */

    public EntityFisherboat(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 6, Sprites.fisherboat);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }

}
