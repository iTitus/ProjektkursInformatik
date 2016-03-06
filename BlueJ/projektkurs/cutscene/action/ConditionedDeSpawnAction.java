package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Bedingte Verschwinde-Action.
 */
public class ConditionedDeSpawnAction extends Action {

    /**
     * CutSceneObject, das verscwinden soll.
     */
    private final CutSceneObject cutSceneObject;

    /**
     * Konstruktor.
     * @param condition
     * Bedingung
     * @param cutSceneObject
     * CutSceneObject
     */
    public ConditionedDeSpawnAction(Condition condition, CutSceneObject cutSceneObject) {
        super(condition);
        this.cutSceneObject = cutSceneObject;
    }

    @Override
    public void doAction(CutScene cutScene) {
        cutScene.deSpawn(cutSceneObject);
    }

}
