package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Positions-Bedingung.
 */
public class PositionCondition extends Condition {

    /**
     * CutSceneObject, deesen Position ueberprueft wird.
     */
    private final CutSceneObject object;
    /**
     * Die X-Koordinate.
     */
    private final int x;
    /**
     * Die Y-Koordinate.
     */
    private final int y;

    /**
     * Konstruktor.
     *
     * @param object
     *            CutSceneObject, dessen Position ueberprueft wird
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public PositionCondition(CutSceneObject object, int x, int y) {
        this.object = object;
        this.x = x;
        this.y = y;
    }

    /**
     * Das CutSceneObject, dessen Postion ueberprueft wird.
     *
     * @return CutSceneObject
     */
    public CutSceneObject getCutSceneObject() {
        return object;
    }

    /**
     * Die X-Koordinate.
     *
     * @return X-Koordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Die Y-Koordinate.
     *
     * @return Y-Koordinate
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        return object.getPosX() == x && object.getPosY() == y;
    }
}
