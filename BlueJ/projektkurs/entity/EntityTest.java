package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.world.Spielfeld;

/**
 *
 */
public class EntityTest extends EntityDialog {

    public EntityTest(Spielfeld map) {
        super(map);
    }

    @Override
    public Dialog getDialog() {
        return Dialoge.test;
    }

}
