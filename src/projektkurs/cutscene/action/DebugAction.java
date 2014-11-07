package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.util.Logger;

public class DebugAction extends Action {

	@Override
	public void doAction(CutScene cutScene) {
		Logger.info(cutScene.getElapsedTicks() + " - FPS: "
				+ CutSceneManager.getFPS() + " - UPS: "
				+ CutSceneManager.getUPS());
	}

}