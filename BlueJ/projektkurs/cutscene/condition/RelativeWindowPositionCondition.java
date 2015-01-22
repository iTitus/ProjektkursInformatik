package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Relative-Positions-Bedingung.
 */
public class RelativeWindowPositionCondition extends PositionCondition {

    /**
     * Konstruktor.
     *
     * @param object
     *            CutSceneObject, dessen Position ueberprueft werden sollo
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
        return getCutSceneObject().getPosX() - CutSceneManager.getCutSceneRenderHelper().getSightX() == getX() && getCutSceneObject().getPosY() - CutSceneManager.getCutSceneRenderHelper().getSightY() == getY();
    }
}
