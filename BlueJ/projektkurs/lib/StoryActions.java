package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projektkurs.io.storage.SaveData;
import projektkurs.story.action.StoryAction;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;
import projektkurs.util.StringUtil;

public final class StoryActions {

    public static final Map<Class<? extends StoryAction>, String> BACK_MAPPINGS = new HashMap<Class<? extends StoryAction>, String>();
    public static final Map<String, Class<? extends StoryAction>> MAPPINGS = new HashMap<String, Class<? extends StoryAction>>();

    public static StoryAction createStoryAction(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        return ReflectionUtil.newInstance(MAPPINGS.get(id));
    }

    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.storyActions", new ArrayList<String>(MAPPINGS.keySet()));
    }

    @Init
    public static void init() {
        // TODO
    }

    public static StoryAction loadStoryAction(SaveData data) {
        StoryAction storyAction = null;
        try {
            storyAction = ReflectionUtil.newInstance(MAPPINGS.get(data.getString("id")));
            storyAction.load(data);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to load StoryAction from " + data, t);
        }
        return storyAction;
    }

    public static SaveData writeStoryAction(StoryAction storyAction) {
        SaveData data = new SaveData();
        try {
            data.set("id", storyAction.getInternalName());
            storyAction.write(data);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to save StoryAction '" + storyAction + "' to SaveData", t);
        }
        return data;
    }

    @SuppressWarnings("unused")
    private static void registerStoryAction(Class<? extends StoryAction> cls, String id) {
        if (cls != null && StringUtil.isNotNullOrEmpty(id) && !MAPPINGS.containsKey(id)) {
            MAPPINGS.put(id, cls);
            BACK_MAPPINGS.put(cls, id);
        } else {
            Logger.warn("Unable to register StoryAction", cls);
            throw new IllegalArgumentException("Unable to register StoryAction " + cls);
        }
    }

    private StoryActions() {
        // NO-OP
    }

}
