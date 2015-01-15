package projektkurs.cutscene;

import java.util.ArrayList;

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
     * Queues für das Verarbeiten der Actions.
     */
    private Queue<Action> actionQueue, startupQueue, tempQueue;
    /**
     * Eventuelles Hintergrundbild.
     */
    private Sprite background;
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
    private final ArrayList<CutSceneObject> objects;

    /**
     * Konstruktor für eine CutScene.
     *
     * @param name
     *            Name
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

    /**
     * CutScene mit Custom Background.
     *
     * @param name
     *            Name
     * @param background
     *            Hintergrundbild
     */
    public CutScene(String name, Sprite background) {
        actionQueue = new Queue<Action>();
        startupQueue = new Queue<Action>();
        tempQueue = new Queue<Action>();
        elapsedTicks = 0;
        isFinished = false;
        objects = new ArrayList<CutSceneObject>();
        this.name = name;
        this.background = background;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Entfernt ein CutSceneObject aus der CutScene.
     *
     * @param object
     *            CutSceneObject
     */
    public void deSpawn(CutSceneObject object) {
        if (object != null) {
            getCutSceneObjectList().remove(object);
        }
    }

    /**
     * Das Hintergrundbild.
     *
     * @return BufferedImage
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * Alle CutSceneObjects in der CutScene.
     *
     * @return ArrayList<CutSceneObject>
     */
    public ArrayList<CutSceneObject> getCutSceneObjectList() {
        return objects;
    }

    /**
     * Seit dem Start der CutScene vergangene Ticks.
     *
     * @return vergangene Ticks
     */
    public int getElapsedTicks() {
        return elapsedTicks;
    }

    /**
     * Der Name der CutScene.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Ist die CutScene beendet.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Braucht die CutScene einen Raster-Hintergrund.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean needsRasterBackground() {
        return background == null;
    }

    /**
     * Registriert eine einmalige Action.
     *
     * @param action
     *            Action
     */
    public void registerStartupAction(Action action) {
        startupQueue.enQueue(action);
    }

    /**
     * Registriert eine Action, die jeden Tick ausgeführt werden kann.
     *
     * @param action
     *            Action
     */
    public void registerTickAction(Action action) {
        actionQueue.enQueue(action);
    }

    /**
     * Setzt das Hintergrundbild.
     *
     * @param background
     *            Hintergrundbild
     */
    public void setBackground(Sprite background) {
        this.background = background;
    }

    /**
     * Markiert die CutScene als beendet.
     */
    public void setFinished() {
        isFinished = true;
    }

    /**
     * Lässt ein CutSceneObject in der CutScene erscheinen.
     *
     * @param object
     *            CutSceneObject
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
