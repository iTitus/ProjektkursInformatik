package projektkurs.cutscene.action;

import projektkurs.cutscene.object.CutSceneObject;

/**
 * Lässt ein CutSceneObject erscheinen.
 */
public class SpawnAction extends ConditionedSpawnAction {

    /**
     * Konstruktor.
     *
     * @param object
     *            CutSceneObject, das erscheinen soll
     */
    public SpawnAction(CutSceneObject object) {
        super(null, object);
    }

}
