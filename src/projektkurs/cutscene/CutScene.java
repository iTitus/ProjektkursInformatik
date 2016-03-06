package projektkurs.cutscene;

import java.util.ArrayList;
import java.util.List;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.render.Sprite;
import projektkurs.util.IUpdatable;
import projektkurs.util.Queue;

/**
 * Eine CutScene.
 */
public final class CutScene implements IUpdatable {

    /**
     * Queues fuer das Verarbeiten der Actions.
     */
    private Queue<Action> actionQueue, startupQueue, tempQueue;
    /**
     * Eventuelles Hintergrundbild.
     */
    private Sprite background;
    private String captionString;
    /**
     * Bereits vergangene Ticks.
     */
    private int elapsedTicks;
    /**
     * Ist die CutScene beendet.
     */
    private boolean isFinished;
    /**
     * Name der CutScene.
     */
    private final String name;
    /**
     * Alle CutSceneObjects in der CutScene.
     */
    private final List<CutSceneObject> objects;

    /**
     * Konstruktor fuer eine CutScene.
     * @param name
     * Name
     */
    public CutScene(String name) {
        actionQueue = new Queue<Action>();
        startupQueue = new Queue<Action>();
        tempQueue = new Queue<Action>();
        elapsedTicks = 0;
        isFinished = false;
        background = null;
        objects = new ArrayList<CutSceneObject>();
        this.name = name;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Entfernt ein CutSceneObject aus der CutScene.
     * @param object
     * CutSceneObject
     */
    public void deSpawn(CutSceneObject object) {
        if (object != null) {
            getCutSceneObjectList().remove(object);
        }
    }

    /**
     * Das Hintergrundbild.
     * @return BufferedImage
     */
    public Sprite getBackground() {
        return background;
    }

    public String getCaptionString() {
        return captionString;
    }

    /**
     * Alle CutSceneObjects in der CutScene.
     * @return ArrayList<CutSceneObject>
     */
    public List<CutSceneObject> getCutSceneObjectList() {
        return objects;
    }

    /**
     * Seit dem Start der CutScene vergangene Ticks.
     * @return vergangene Ticks
     */
    public int getElapsedTicks() {
        return elapsedTicks;
    }

    /**
     * Der Name der CutScene.
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Ist die CutScene beendet.
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Braucht die CutScene einen Raster-Hintergrund.
     * @return true, wenn ja; false, wenn nein
     */
    public boolean needsRasterBackground() {
        return background == null;
    }

    /**
     * Registriert eine einmalige Action.
     * @param action
     * Action
     */
    public void registerStartupAction(Action action) {
        startupQueue.enQueue(action);
    }

    /**
     * Registriert eine Action, die jeden Tick ausgefuehrt werden kann.
     * @param action
     * Action
     */
    public void registerTickAction(Action action) {
        actionQueue.enQueue(action);
    }

    public void reset() {
        elapsedTicks = 0;
        isFinished = false;
        objects.clear();
    }

    /**
     * Setzt das Hintergrundbild.
     * @param background
     * Hintergrundbild
     */
    public void setBackground(Sprite background) {
        this.background = background;
    }

    public void setCaptionString(String captionString) {
        this.captionString = captionString;
    }

    /**
     * Markiert die CutScene als beendet.
     */
    public void setFinished() {
        isFinished = true;
    }

    /**
     * Laesst ein CutSceneObject in der CutScene erscheinen.
     * @param object
     * CutSceneObject
     */
    public void spawn(CutSceneObject object) {
        if (object != null) {
            getCutSceneObjectList().add(object);
        }
    }

    /**
     * Updated die CutScene.
     */
    @Override
    public void update() {

        if (isFinished) {
            return;
        }

        if (elapsedTicks == 0 && startupQueue != null && !startupQueue.empty()) {

            Action action;
            while (!startupQueue.empty()) {
                action = startupQueue.frontDeQueue();
                if (action != null) {
                    action.doAction(this);
                }
            }

            startupQueue = null;

        }
        if (actionQueue != null && !actionQueue.empty()) {

            tempQueue = new Queue<Action>();

            Action action;
            while (!actionQueue.empty()) {
                action = actionQueue.frontDeQueue();

                if (action != null && action.shouldDoAction(this)) {
                    action.doAction(this);
                }
                tempQueue.enQueue(action);
            }
            actionQueue = tempQueue;
        } else {
            isFinished = true;
        }

        elapsedTicks++;

    }

}
