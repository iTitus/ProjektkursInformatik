package projektkurs.lib;

import java.util.HashMap;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.ConditionedMoveAction;
import projektkurs.cutscene.action.ConditionedMoveSightAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.action.SpawnAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Init;

/**
 * Alle CutScenes.
 */
public final class CutScenes {

  /**
   * Die Zurück-Mappings.
   */
  public static final HashMap<CutScene, String> BACK_MAPPINGS = new HashMap<CutScene, String>();
  /**
   * CutScene No. 1
   */
  public static CutScene                        cutSceneOne;
  /**
   * Die Mappings.
   */
  public static final HashMap<String, CutScene> MAPPINGS      = new HashMap<String, CutScene>();

  /**
   * Initialisiert alle CutScenes.
   */
  @Init
  public static void init() {

    cutSceneOne = new CutScene();

    CutSceneObject auto = new CutSceneObject(Images.item42, 10, 10, 3, 3);
    CutSceneObject auto2 = new CutSceneObject(Images.item42, 0, 0, 1, 1);

    cutSceneOne.registerStartupAction(new SpawnAction(auto));
    cutSceneOne.registerStartupAction(new SpawnAction(auto2));

    cutSceneOne.registerTickAction(new ConditionedMoveAction(new TickCondition(TickConditionType.MODULO_0, 20), auto, 1, 1));
    cutSceneOne.registerTickAction(new ConditionedMoveSightAction(new TickCondition(TickConditionType.MODULO_0, 20), 1, 1));
    cutSceneOne.registerTickAction(new DebugAction());
    cutSceneOne.registerTickAction(new ConditionedExitAction(new TickCondition(TickConditionType.GREATER, 600)));
    cutSceneOne.registerTickAction(new ConditionedMoveAction(new TickCondition(TickConditionType.MODULO_0, 10), auto2, 1, 1));

    registerMapping("CutSceneOne", cutSceneOne);

  }

  /**
   * Registriert ein Mapping.
   *
   * @param name
   *          Name
   * @param c
   *          CutScene
   */
  private static void registerMapping(String name, CutScene c) {
    MAPPINGS.put(name, c);
    BACK_MAPPINGS.put(c, name);
  }

  /**
   * Nicht instanziierbar.
   */
  private CutScenes() {
  }

}
