package projektkurs.story;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.story.trigger.AbstractTrigger;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MethodInvoker;

/**
 * Der Storymanager.
 */
public class StoryManager implements IUpdatable, ISaveable {

    private final StoryContext storyContext;
    /**
     * Alle Trigger.
     */
    private final Map<AbstractTrigger, MethodInvoker> triggerMap;
    /**
     * Hinzuzufuegende Trigger.
     */
    private final Map<AbstractTrigger, MethodInvoker> triggerToAddMap;

    /**
     * Zu loeschende Trigger.
     */
    private final Set<AbstractTrigger> triggerToRemove;

    /**
     * Konstruktor.
     */
    public StoryManager() {
        storyContext = new StoryContext(this);
        triggerMap = new HashMap<AbstractTrigger, MethodInvoker>();
        triggerToAddMap = new HashMap<AbstractTrigger, MethodInvoker>();
        triggerToRemove = new HashSet<AbstractTrigger>();
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public StoryContext getStoryContext() {
        return storyContext;
    }

    public Map<AbstractTrigger, MethodInvoker> getTriggerMap() {
        return triggerMap;
    }

    @Override
    public void load(SaveData data) {
        // TODO
    }

    /**
     * Registriert einen neuen Trigger hinzu.
     * @param trigger
     * hinzuzufuegender Trigger
     * @param m
     * auszufuehrende Methode
     * @param objects
     * eventuelle Parameter
     */
    public void registerTrigger(AbstractTrigger trigger, Method m, Object... objects) {
        if (triggerMap.containsKey(trigger)) {
            Logger.logThrowable("Unable to register Trigger '" + trigger.getClass() + "'", new IllegalArgumentException("'" + trigger.getClass() + "' is already registered"));
        } else {
            triggerToAddMap.put(trigger, new MethodInvoker(m, objects));
        }
    }

    /**
     * Entfernt einen Trigger.
     * @param trigger
     * zu entfernender Trigger.
     */
    public void removeTrigger(AbstractTrigger trigger) {
        triggerToRemove.add(trigger);
    }

    /**
     * Updated alle Trigger.
     */
    @Override
    public void update() {
        triggerMap.putAll(triggerToAddMap);
        triggerToAddMap.clear();
        for (Entry<AbstractTrigger, MethodInvoker> entry : triggerMap.entrySet()) {
            if (entry.getKey().isTriggerActive()) {
                entry.getValue().invoke();
                if (entry.getKey().shouldRemove()) {
                    removeTrigger(entry.getKey());
                }
            }
        }
        for (AbstractTrigger toRemove : triggerToRemove) {
            triggerMap.remove(toRemove);
        }
        triggerToRemove.clear();
    }

    @Override
    public void write(SaveData data) {
        // TODO
    }
}
