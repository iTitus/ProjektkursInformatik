package projektkurs.story;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;

public class StoryContext implements ISaveable {

    private final StoryManager storyManager;
    private SaveData variables;

    public StoryContext(StoryManager storyManager) {
        variables = new SaveData();
        this.storyManager = storyManager;
    }

    public StoryManager getStoryManager() {
        return storyManager;
    }

    public SaveData getVariables() {
        return variables;
    }

    @Override
    public void load(SaveData data) {
        variables = variables.getSaveData("variables");
    }

    @Override
    public void write(SaveData data) {
        data.set("variables", variables);
    }

}
