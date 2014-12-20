package projektkurs.story.trigger;

/**
 * Ein Trigger, der mehrere andere Trigger in sich vereint.
 */
public abstract class CombinedTrigger extends Trigger {

    /**
     * Die Trigger.
     */
    protected final Trigger[] triggers;

    /**
     * Konstruktor.
     *
     * @param triggers
     *            die Trigger
     */
    public CombinedTrigger(Trigger... triggers) {
        this.triggers = triggers;
    }

}
