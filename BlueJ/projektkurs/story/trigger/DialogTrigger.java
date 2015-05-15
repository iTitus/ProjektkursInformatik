package projektkurs.story.trigger;

import projektkurs.Main;
import projektkurs.io.storage.SaveData;

public class DialogTrigger extends AbstractTrigger {

    private int value;

    public DialogTrigger(int value) {
        this.value = value;
    }

    @Override
    public boolean isTriggerActive() {
        return Main.getLevel().getDialogManager().getValue() == value;
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        value = data.getInteger("value");
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set("value", value);
    }
}
