package projektkurs.story.trigger;

/**
 * Ein Trigger.
 */
public abstract class Trigger {

    protected boolean shouldRemove = true;

    /**
     * Ist dieser Trigger aktiv.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public abstract boolean isTriggerActive();

    public Trigger setMultipleTimeTrigger() {
        shouldRemove = false;
        return this;
    }

    public Trigger setOneTimeTrigger() {
        shouldRemove = true;
        return this;
    }

    /**
     * Soll dieser Trigger entfernt werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldRemove() {
        return shouldRemove;
    }
}
