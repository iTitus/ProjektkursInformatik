package projektkurs.story.trigger;

/**
 * Ein Trigger, der mehrere andere Trigger in sich vereint.
 */
public abstract class CombinedTrigger implements ITrigger {

    /**
     * Die Trigger.
     */
    protected final ITrigger[] trigger;

    /**
     * Konstruktor.
     *
     * @param triggers
     *            die Trigger
     */
    public CombinedTrigger(ITrigger... triggers) {
        trigger = triggers;
    }

}
