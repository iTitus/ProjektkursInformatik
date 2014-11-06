package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Spawn ein CutSceneObject - sollte am besten nur der Startup-Schlange
 * hinzugefügt werden
 */
public class SpawnAction extends Action {

	private final CutSceneObject object;

	public SpawnAction(CutSceneObject object) {
		this.object = object;
	}

	@Override
	public void doAction(CutScene cutScene) {
		cutScene.spawn(object);
	}

}
