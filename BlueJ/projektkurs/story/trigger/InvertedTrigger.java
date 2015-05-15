package projektkurs.story.trigger;

import projektkurs.io.storage.SaveData;
import projektkurs.lib.Trigger;

public class InvertedTrigger extends AbstractTrigger {

    private AbstractTrigger trigger;

    public InvertedTrigger(AbstractTrigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean isTriggerActive() {
        return !trigger.isTriggerActive();
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        trigger = Trigger.loadTrigger(data.getSaveData("trigger"));
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set("trigger", Trigger.writeTrigger(trigger));
    }
}
