package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.action.ConditionedMoveAction;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Relative-Positions-Bedingung.
 */
public class RelativeWindowPositionCondition extends PositionCondition {

    /**
     * Konstruktor.
     *
     * @param object
     *            CutSceneObject, dessen Position überprüft werden sollo
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public RelativeWindowPositionCondition(CutSceneObject object, int x, int y) {
        super(object, x, y);
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        return ((ConditionedMoveAction) action).getCutSceneObject().getPosX() - CutSceneManager.getCutSceneRenderHelper().getSightX() == getX() && ((ConditionedMoveAction) action).getCutSceneObject().getPosY() - CutSceneManager.getCutSceneRenderHelper().getSightY() == getY();
    }
}
