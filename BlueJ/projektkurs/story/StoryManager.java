package projektkurs.story;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import projektkurs.story.trigger.Trigger;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MethodInvoker;

/**
 * Der Storymanager.
 */
public class StoryManager implements IUpdatable {

    private int progress;

    /**
     * Alle Trigger.
     */
    private final HashMap<Trigger, MethodInvoker> triggerMap;
    /**
     * Hinzuzufuegende Trigger.
     */
    private final HashMap<Trigger, MethodInvoker> triggerToAddMap;
    /**
     * Zu loeschende Trigger.
     */
    private final ArrayList<Trigger> triggerToRemove;

    /**
     * Konstruktor.
     */
    public StoryManager() {
        triggerMap = new HashMap<Trigger, MethodInvoker>();
        triggerToAddMap = new HashMap<Trigger, MethodInvoker>();
        triggerToRemove = new ArrayList<Trigger>();
        progress = 0;
    }

    public void addProgress() {
        incrementProgress(1);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public int getProgress() {
        return progress;
    }

    public HashMap<Trigger, MethodInvoker> getTriggerMap() {
        return triggerMap;
    }

    public void incrementProgress(int by) {
        progress += by;
    }

    /**
     * Registriert einen neuen Trigger hinzu.
     *
     * @param trigger
     *            hinzuzufuegender Trigger
     * @param m
     *            auszufuehrende Methode
     * @param objects
     *            eventuelle Parameter
     */
    public void registerTrigger(Trigger trigger, Method m, Object... objects) {
        if (triggerMap.containsKey(trigger)) {
            Logger.logThrowable("Unable to register Trigger '" + trigger.getClass() + "'", new IllegalArgumentException("'" + trigger.getClass() + "' is already registered"));
        } else {
            triggerToAddMap.put(trigger, new MethodInvoker(m, objects));
        }
    }

    /**
     * Entfernt einen Trigger.
     *
     * @param trigger
     *            zu entfernender Trigger.
     */
    public void removeTrigger(Trigger trigger) {
        triggerToRemove.remove(trigger);
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * Updated alle Trigger.
     */
    @Override
    public void update() {
        triggerMap.putAll(triggerToAddMap);
        triggerToAddMap.clear();
        for (Entry<Trigger, MethodInvoker> entry : triggerMap.entrySet()) {
            if (entry.getKey().isTriggerActive()) {
                entry.getValue().invoke();
                if (entry.getKey().shouldRemove()) {
                    triggerToRemove.add(entry.getKey());
                }
            }
        }
        for (Trigger toRemove : triggerToRemove) {
            triggerMap.remove(toRemove);
        }
        triggerToRemove.clear();

    }

}
