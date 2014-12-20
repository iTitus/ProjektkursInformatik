package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * Condition, die mehrere Conditions mit UND verbindet.
 */
public class CombinedAndCondition extends CombinedCondition {

    /**
     * Konstruktor.
     *
     * @param conditions
     *            Zu prüfende Conditions.
     */
    public CombinedAndCondition(Condition... conditions) {
        super(conditions);
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        for (Condition condition : conditions) {
            if (!condition.isTrue(action, cutScene)) {
                return false;
            }
        }
        return true;
    }
}
