package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.ConditionedMoveAction;
import projektkurs.cutscene.action.ConditionedMoveSightAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.action.SpawnAction;
import projektkurs.cutscene.condition.CombinedAndCondition;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.condition.TickIntervalCondition;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Init;
import projektkurs.util.Pair;

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
    public static CutScene cutSceneOne;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, CutScene> MAPPINGS = new HashMap<String, CutScene>();

    /**
     * Das Pair, das alle CutScenes enthält.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.cutscenes", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle CutScenes.
     */
    @Init
    public static void init() {

        cutSceneOne = new CutScene(Images.rasen);

        CutSceneObject auto = new CutSceneObject(Images.item42, 10, 10, 3, 3);
        // CutSceneObject straße = new CutSceneObject(Images.straße, 0, 0, 1, 1);
        CutSceneObject figur = new CutSceneObject(Images.key, 5, 5, 1, 1);
        CutSceneObject hafen = new CutSceneObject(Images.baum, 50, 7, 9, 9);
        // CutSceneObject ... = new CutSceneObject(Images., 0, 0, 1, 1);
        // CutSceneObject ... = new CutSceneObject(Images., 0, 0, 1, 1);
        // CutSceneObject ... = new CutSceneObject(Images., 0, 0, 1, 1);
        // CutSceneObject ... = new CutSceneObject(Images., 0, 0, 1, 1);
        // CutSceneObject ... = new CutSceneObject(Images., 0, 0, 1, 1);

        cutSceneOne.registerStartupAction(new SpawnAction(auto));
        // cutSceneOne.registerStartupAction(new SpawnAction(straße));
        cutSceneOne.registerStartupAction(new SpawnAction(figur));
        cutSceneOne.registerStartupAction(new SpawnAction(hafen));
        // cutSceneOne.registerStartupAction(new SpawnAction());
        // cutSceneOne.registerStartupAction(new SpawnAction());
        // cutSceneOne.registerStartupAction(new SpawnAction());
        cutSceneOne.registerTickAction(new ConditionedMoveAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 20), new TickIntervalCondition(0, 79)), auto, 1, 0));
        cutSceneOne.registerTickAction(new ConditionedMoveSightAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 20), new TickIntervalCondition(80, 200)), 1, 0));
        cutSceneOne.registerTickAction(new DebugAction());
        cutSceneOne.registerTickAction(new ConditionedExitAction(new TickCondition(TickConditionType.GREATER, 600)));

        registerMapping("one", cutSceneOne);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param c
     *            CutScene
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
