package projektkurs.cutscene.render.object;

import projektkurs.cutscene.object.CutSceneObject;

public class RenderCutSceneObject {

	private final CutSceneObject object;

	public RenderCutSceneObject(CutSceneObject object) {
		this.object = object;
	}

	public CutSceneObject getCutSceneObject() {
		return object;
	}

}
