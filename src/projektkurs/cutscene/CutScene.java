package projektkurs.cutscene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Queue;

/**
 * Eine CutScene
 */
public class CutScene {

	private Queue<Action> actionQueue, startupQueue, tempQueue;
	private BufferedImage background;
	private int elapsedTicks;
	private boolean isFinished;
	private final ArrayList<CutSceneObject> objects;
	private final boolean rasterBackground;

	/**
	 * Konstruktor für eine CutScene
	 */
	public CutScene() {
		actionQueue = new Queue<Action>();
		startupQueue = new Queue<Action>();
		tempQueue = new Queue<Action>();
		elapsedTicks = 0;
		isFinished = false;
		rasterBackground = true;
		background = null;
		objects = new ArrayList<CutSceneObject>();
	}

	/**
	 * CutScene mit Custom Background
	 * 
	 * @param background
	 */
	public CutScene(BufferedImage background) {
		actionQueue = new Queue<Action>();
		startupQueue = new Queue<Action>();
		tempQueue = new Queue<Action>();
		elapsedTicks = 0;
		isFinished = false;
		rasterBackground = false;
		this.background = background;
		objects = new ArrayList<CutSceneObject>();
	}

	public void deSpawn(CutSceneObject object) {
		if (object != null) {
			getCutSceneObjectList().remove(object);
		}
	}

	public BufferedImage getBackground() {
		return background;
	}

	public ArrayList<CutSceneObject> getCutSceneObjectList() {
		return objects;
	}

	public int getElapsedTicks() {
		return elapsedTicks;
	}

	/**
	 * @return
	 */
	public boolean isFinished() {
		return isFinished;
	}

	public boolean needsRasterBackground() {
		return rasterBackground;
	}

	/**
	 * Registriert eine einmalige Aktion
	 *
	 * @param action
	 */
	public void registerStartupAction(Action action) {
		startupQueue.enQueue(action);
	}

	/**
	 * @param action
	 */
	public void registerTickAction(Action action) {
		actionQueue.enQueue(action);
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	/**
     *
     */
	public void setFinished() {
		isFinished = true;
	}

	public void spawn(CutSceneObject object) {
		if (object != null) {
			getCutSceneObjectList().add(object);
		}
	}

	public void update() {

		if (elapsedTicks == 0 && startupQueue != null && !startupQueue.empty()) {

			Action currAction;
			while (!startupQueue.empty()) {
				currAction = startupQueue.frontDeQueue();
				if (currAction != null)
					currAction.doAction(this);
			}

			startupQueue = null;

		}
		if (actionQueue != null && !actionQueue.empty()) {

			tempQueue = new Queue<Action>();

			Action currAction;
			while (!actionQueue.empty()) {
				currAction = actionQueue.frontDeQueue();

				if (currAction != null && currAction.shouldDoAction(this))
					currAction.doAction(this);
				tempQueue.enQueue(currAction);
			}
			actionQueue = tempQueue;
		} else {
			isFinished = true;
		}

		elapsedTicks++;

	}

}