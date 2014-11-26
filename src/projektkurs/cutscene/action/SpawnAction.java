package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Spawn ein CutSceneObject - sollte am besten nur der Startup-Schlange hinzugefügt werden.
 */
public class SpawnAction extends Action {

    /**
     * CutSceneObject, das gespawnt werden soll.
     */
    private final CutSceneObject object;

    /**
     * Konstruktor.
     *
     * @param object
     *            CutSceneObject, das gespawnt werden soll
     */
    public SpawnAction(CutSceneObject object) {
        super();
        this.object = object;
    }

    @Override
    public void doAction(CutScene cutScene) {
        cutScene.spawn(object);
    }

}
