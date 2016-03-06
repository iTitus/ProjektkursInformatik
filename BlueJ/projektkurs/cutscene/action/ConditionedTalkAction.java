package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;

/**
 * Eine Action, die einen Dialog beginnt.
 */
public class ConditionedTalkAction extends Action {

    /**
     * Konstruktor.
     * @param condition
     * Ausfuehrbedingung
     */
    public ConditionedTalkAction(Condition condition) {
        super(condition);
    }

    @Override
    public void doAction(CutScene cutScene) {
        // Dialog
    }

}
