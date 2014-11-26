package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;

/**
 * Eine Action, die die CutScene beendet.
 */
public class ConditionedExitAction extends Action {

    /**
     * Konstruktor.
     *
     * @param condition
     *            Ausführbedingung
     */
    public ConditionedExitAction(Condition condition) {
        super(condition);
    }

    @Override
    public void doAction(CutScene cutScene) {
        cutScene.setFinished();
    }

}
