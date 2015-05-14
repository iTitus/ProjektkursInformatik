package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Spawn ein CutSceneObject - sollte am besten nur der Startup-Schlange hinzugefuegt werden.
 */
public class ConditionedSpawnAction extends Action {

	/**
	 * CutSceneObject, das gespawnt werden soll.
	 */
	private final CutSceneObject object;

	/**
	 * Konstruktor.
	 *
	 * @param condition Ausfuehrbedingung
	 * @param object    CutSceneObject, das gespawnt werden soll
	 */
	public ConditionedSpawnAction(Condition condition, CutSceneObject object) {
		super(condition);
		this.object = object;
	}

	@Override
	public void doAction(CutScene cutScene) {
		cutScene.spawn(object);
	}

	/**
	 * Das zu spawnende CutSceneObject.
	 *
	 * @return CutSceneObject
	 */
	public CutSceneObject getCutSceneObject() {
		return object;
	}

}
