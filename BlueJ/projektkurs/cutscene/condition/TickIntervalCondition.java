package projektkurs.cutscene.condition;

import projektkurs.cutscene.condition.TickCondition.TickConditionType;

/**
 * Eine Condition, die nur innerhalb eines gegebenen Intervalls true ist.
 */
public class TickIntervalCondition extends CombinedAndCondition {

    /**
     * Konstruktor.
     *
     * @param minTick
     *            Unterer Rand des Intervalls
     * @param maxTick
     *            Oberer Rand des Intervalls
     */
    public TickIntervalCondition(int minTick, int maxTick) {
        super(new TickCondition(TickConditionType.GREATER_EQUALS, minTick), new TickCondition(TickConditionType.LESSER_EQUALS, maxTick));
    }

}
