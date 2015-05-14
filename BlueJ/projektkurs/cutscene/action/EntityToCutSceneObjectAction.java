package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;

public class EntityToCutSceneObjectAction extends Action {

	private final boolean spawnPlayer;

	public EntityToCutSceneObjectAction() {
		this(true);
	}

	public EntityToCutSceneObjectAction(boolean spawnPlayer) {
		super(null);
		this.spawnPlayer = spawnPlayer;
	}

	@Override
	public void doAction(CutScene cutScene) {
		for (Entity e : CutSceneManager.getMap().getEntityList()) {
			if (!spawnPlayer && e instanceof EntityPlayer) {
				continue;
			}
			CutSceneObject o = new CutSceneObject(e.getSprite(), e.getPosX(), e.getPosY(), e.getSizeX(), e.getSizeY());
			cutScene.spawn(o);
		}
	}
}
