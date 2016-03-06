package projektkurs.story.trigger;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.Trigger;

/**
 * Ein Trigger.
 */
public abstract class AbstractTrigger implements ISaveable {

    protected boolean shouldRemove;

    public AbstractTrigger() {
        shouldRemove = true;
    }

    public final String getInternalName() {
        return Trigger.BACK_MAPPINGS.get(getClass());
    }

    /**
     * Ist dieser Trigger aktiv.
     * @return true, wenn ja; false, wenn nein
     */
    public abstract boolean isTriggerActive();

    @Override
    public void load(SaveData data) {
        shouldRemove = data.getBoolean("shouldRemove");
    }

    public AbstractTrigger setMultipleTimeTrigger() {
        shouldRemove = false;
        return this;
    }

    public AbstractTrigger setOneTimeTrigger() {
        shouldRemove = true;
        return this;
    }

    /**
     * Soll dieser Trigger entfernt werden.
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldRemove() {
        return shouldRemove;
    }

    @Override
    public void write(SaveData data) {
        data.set("shouldRemove", shouldRemove);
    }
}
