package projektkurs.story.trigger;

import projektkurs.io.storage.SaveData;
import projektkurs.lib.Trigger;

/**
 * Ein Trigger, der mehrere andere Trigger in sich vereint.
 */
public abstract class CombinedTrigger extends AbstractTrigger {

    /**
     * Die Trigger.
     */
    protected AbstractTrigger[] triggers;

    /**
     * Konstruktor.
     * @param triggers
     * die Trigger
     */
    public CombinedTrigger(AbstractTrigger... triggers) {
        this.triggers = triggers;
    }

    public AbstractTrigger[] getTriggers() {
        return triggers;
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        triggers = new AbstractTrigger[data.getInteger("triggerCount")];
        for (int i = 0; i < triggers.length; i++) {
            triggers[i] = Trigger.loadTrigger(data.getSaveData("trigger" + i));
        }
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set("triggerCount", triggers.length);
        for (int i = 0; i < triggers.length; i++) {
            data.set("trigger" + i, Trigger.writeTrigger(triggers[i]));
        }
    }
}
