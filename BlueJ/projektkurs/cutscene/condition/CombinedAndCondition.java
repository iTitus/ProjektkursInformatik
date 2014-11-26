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
        for (int i = 0; i < conditions.length; i++) {
            if (!conditions[i].isTrue(action, cutScene)) {
                return false;
            }
        }
        return true;
    }
}
