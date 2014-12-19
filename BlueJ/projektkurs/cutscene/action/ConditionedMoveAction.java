package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Eine Action, die ein CutSceneObject bewegt.
 */
public class ConditionedMoveAction extends Action {

    /**
     * Bewegung in x-Richtung.
     */
    private final int dx;
    /**
     * Bewegung in y-Richtung.
     */
    private final int dy;
    /**
     * Das CutSceneObject, das bewegt werden soll.
     */
    private final CutSceneObject object;

    /**
     * @param condition
     *            Ausführbedingung
     * @param object
     *            Das CutSceneObject, das bewegt werden soll
     * @param dx
     *            Bewegung in x-Richtung
     * @param dy
     *            Bewegung in y-Richtung
     */
    public ConditionedMoveAction(Condition condition, CutSceneObject object, int dx, int dy) {
        super(condition);
        this.object = object;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void doAction(CutScene cutScene) {
        object.moveBy(dx, dy);
    }

    /**
     * Das CutSceneObject, das bewegt werden soll.
     *
     * @return CutSceneObject
     */
    public CutSceneObject getCutSceneObject() {
        return object;
    }

}
