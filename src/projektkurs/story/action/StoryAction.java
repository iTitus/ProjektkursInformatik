package projektkurs.story.action;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.StoryActions;

public abstract class StoryAction implements ISaveable {

    protected StoryAction next;

    public void execute() {
        doExecute();
        if (next != null) {
            next.execute();
        }
    }

    public final String getInternalName() {
        return StoryActions.BACK_MAPPINGS.get(getClass());
    }

    public StoryAction getNext() {
        return next;
    }

    public StoryAction getNextAction() {
        return next;
    }

    @Override
    public void load(SaveData data) {
        if (data.hasKey("nextAction")) {
            next = StoryActions.loadStoryAction(data.getSaveData("nextAction"));
        }
    }

    /**
     * Setzt die nächste StoryAction
     *
     * @param nextAction
     *            die nächste StoryAction
     * @return die nächste StoryAction
     */
    public StoryAction setNext(StoryAction nextAction) {
        next = nextAction;
        return nextAction;
    }

    @Override
    public void write(SaveData data) {
        if (next != null) {
            data.set("nextAction", StoryActions.writeStoryAction(next));
        }
    }

    protected abstract void doExecute();

}
