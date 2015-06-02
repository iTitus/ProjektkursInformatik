package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityDoor extends EntityDialog {
    public EntityDoor(Spielfeld map) {
        super(map);
    }

    public EntityDoor(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.door_NS);
    }

    @Override
    public Dialog getDialog() {
        if((1<<18) == (map.getLevel().getDialogManager().getValue() & (1<<18))) {
        	return Dialoge.LVmFaehrmannEnd;
        } else if(((1<<0)|(1<<1)) == (map.getLevel().getDialogManager().getValue() & ((1<<0)|(1<<1)))) {
        	return Dialoge.LVmFaehrmannOne;
        } else if((0|(1)) == (map.getLevel().getDialogManager().getValue() & (0|(1)))) {
        	return Dialoge.LVmFaehrmannNull;
        } else return Dialoge.test;
    }
}
