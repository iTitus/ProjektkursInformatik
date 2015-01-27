package projektkurs.cutscene.action;

import projektkurs.Main;
import projektkurs.entity.Entity;

public class DespawnPlayerAction extends DespawnAction {

    public DespawnPlayerAction() {
    }

    @Override
    protected Entity getEntity() {
        return Main.getPlayer();
    }
}
