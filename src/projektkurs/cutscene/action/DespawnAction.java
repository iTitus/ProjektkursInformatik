package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.entity.Entity;

public abstract class DespawnAction extends Action {

    public DespawnAction() {
    }

    @Override
    public void doAction(CutScene cutScene) {
        Entity e = getEntity();
        cutScene.deSpawn(new CutSceneObject(e.getSprite(), e.getPosX(), e.getPosY(), e.getSizeX(), e.getSizeY()));
    }

    protected abstract Entity getEntity();
}
