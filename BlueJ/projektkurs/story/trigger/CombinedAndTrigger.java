package projektkurs.story.trigger;

/**
 * Trigger, der mehrere Trigger mit UND verbindet.
 */
public class CombinedAndTrigger extends CombinedTrigger {

    /**
     * Konstruktor.
     *
     * @param trigger
     *            die Trigger
     */
    public CombinedAndTrigger(ITrigger... trigger) {
        super(trigger);
    }

    @Override
    public boolean isTriggerActive() {
        for (int i = 0; i < trigger.length; i++) {
            if (!trigger[i].isTriggerActive()) {
                return false;
            }
        }
        return true;
    }

}
