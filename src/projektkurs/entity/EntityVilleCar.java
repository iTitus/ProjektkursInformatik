package projektkurs.entity;

import projektkurs.dialog.Dialog;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityVilleCar extends EntityDialog {

    public EntityVilleCar(Spielfeld map) {
        super(map);
    }

    public EntityVilleCar(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 4, Sprites.car_frauV);
    }

    @Override
    public Dialog getDialog() {

    	if((1<<19) == (map.getLevel().getDialogManager().getValue() & (1<<19))) {
    		return Dialoge.LVmFrauEnd;
    	} else if((1<<15) == (map.getLevel().getDialogManager().getValue() & (1<<15))) {
    		return Dialoge.LVmFrauStd;
    	} else if(((1<<12)|(1<<13)|(1<<14)) == (map.getLevel().getDialogManager().getValue() & ((1<<12)|(1<<13)|(1<<14)))) {
    		return Dialoge.LVmFrauThree;
    	} else if(((1<<2)|(1<<3)|(1<<4)|(1<<5)) == (map.getLevel().getDialogManager().getValue() & ((1<<2)|(1<<3)|(1<<4)|(1<<5)))) {
    		return Dialoge.LVmFrauTwo;
    	} else if(((1<<0)|(1<<1)) == (map.getLevel().getDialogManager().getValue() & ((1<<0)|(1<<1)))) {
    		return Dialoge.LVmFrauOne;
    	} else if((0|(1)) == (map.getLevel().getDialogManager().getValue() & (0|(1)))) {
    		return Dialoge.LVmFrauNull;
    	} else return Dialoge.test;
    	
        

    }

}
