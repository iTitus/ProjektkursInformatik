package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;

public class AreaCondition extends PositionCondition {

    private final int sizeX;
    private final int sizeY;

    /**
     * @param object
     * @param x
     * @param y
     * @param sizeX
     * @param sizeY
     */
    public AreaCondition(CutSceneObject object, int x, int y, int sizeX, int sizeY) {
        super(object, x, y);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        return getCutSceneObject().isInside(getX(), getY(), sizeX, sizeY);
    }

}
