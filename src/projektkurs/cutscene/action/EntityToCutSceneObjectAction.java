package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.entity.Entity;

public class EntityToCutSceneObjectAction extends Action {

    public EntityToCutSceneObjectAction() {
    }

    @Override
    public void doAction(CutScene cutScene) {
        for (Entity e : CutSceneManager.getMap().getEntityList()) {
            CutSceneObject o = new CutSceneObject(e.getSprite(), e.getPosX(), e.getPosY(), e.getSizeX(), e.getSizeY());
            cutScene.spawn(o);
        }
    }
}
