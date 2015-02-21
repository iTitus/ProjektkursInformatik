package projektkurs.story.trigger;

import projektkurs.Main;

public class ProgressTrigger extends Trigger {

    private final int progress;

    public ProgressTrigger(int progress) {
        this.progress = progress;
    }

    @Override
    public boolean isTriggerActive() {
        return Main.getLevel().getMap().getStoryManager().getProgress() >= progress;
    }

}
