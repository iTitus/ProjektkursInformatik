package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * Condition, die mehrere Conditions mit ODER verbindet.
 */
public class CombinedOrCondition extends CombinedCondition {

    /**
     * Konstruktor.
     *
     * @param conditions
     *            Zu pruefende Conditions.
     */
    public CombinedOrCondition(Condition... conditions) {
        super(conditions);
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        for (Condition condition : conditions) {
            if (condition.isTrue(action, cutScene)) {
                return true;
            }
        }
        return false;
    }

}
