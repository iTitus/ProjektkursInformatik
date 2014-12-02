package projektkurs.cutscene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Queue;

/**
 * Eine CutScene.
 */
public final class CutScene {

    /**
     * Queues für das Verarbeiten der Actions.
     */
    private Queue<Action> actionQueue, startupQueue, tempQueue;
    /**
     * Eventuelles Hintergrundbild.
     */
    private BufferedImage background;
    /**
     * Bereits vergangene Ticks.
     */
    private int elapsedTicks;
    /**
     * Ist die CutScene beendet.
     */
    private boolean isFinished;
    /**
     * Alle CutSceneObjects in der CutScene.
     */
    private final ArrayList<CutSceneObject> objects;

    /**
     * Konstruktor für eine CutScene.
     */
    public CutScene() {
        actionQueue = new Queue<Action>();
        startupQueue = new Queue<Action>();
        tempQueue = new Queue<Action>();
        elapsedTicks = 0;
        isFinished = false;
        background = null;
        objects = new ArrayList<CutSceneObject>();
    }

    /**
     * CutScene mit Custom Background.
     *
     * @param background
     *            Hintergrundbild
     */
    public CutScene(BufferedImage background) {
        actionQueue = new Queue<Action>();
        startupQueue = new Queue<Action>();
        tempQueue = new Queue<Action>();
        elapsedTicks = 0;
        isFinished = false;
        this.background = background;
        objects = new ArrayList<CutSceneObject>();
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
    public BufferedImage getBackground() {
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
    public void setBackground(BufferedImage background) {
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
    public void update() {

        if (elapsedTicks == 0 && startupQueue != null && !startupQueue.empty()) {

            Action currAction;
            while (!startupQueue.empty()) {
                currAction = startupQueue.frontDeQueue();
                if (currAction != null) {
                    currAction.doAction(this);
                }
            }

            startupQueue = null;

        }
        if (actionQueue != null && !actionQueue.empty()) {

            tempQueue = new Queue<Action>();

            Action currAction;
            while (!actionQueue.empty()) {
                currAction = actionQueue.frontDeQueue();

                if (currAction != null && currAction.shouldDoAction(this)) {
                    currAction.doAction(this);
                }
                tempQueue.enQueue(currAction);
            }
            actionQueue = tempQueue;
        } else {
            isFinished = true;
        }

        elapsedTicks++;

    }

}
