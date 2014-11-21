package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;

/**
 * Eine Condition, die nur innerhalb eines gegebenen Intervalls true ist.
 */
public class TickIntervalCondition extends Condition {

    /**
     * Die Hilfs-Condition.
     */
    private final Condition condition;

    /**
     * Konstruktor.
     *
     * @param minTick
     *            Unterer Rand des Intervalls
     * @param maxTick
     *            Oberer Rand des Intervalls
     */
    public TickIntervalCondition(int minTick, int maxTick) {
        condition = new CombinedAndCondition(new TickCondition(TickConditionType.GREATER_EQUALS, minTick), new TickCondition(TickConditionType.LESSER_EQUALS,
                maxTick));
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        return condition.isTrue(action, cutScene);
    }

}
