package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedClearMapAction;
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
import projektkurs.util.Init.State;
import projektkurs.util.Pair;
import projektkurs.lib.CutSceneUtil;

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
     * CutScene No. 2
     */
    public static CutScene cutSceneTwo;
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
    @SuppressWarnings("unused")
    @Init(state = State.RESOURCES)
    public static void init() {
        CutScene one = new CutScene();

        // CutSceneObject auto = new CutSceneObject(Images.nuke, 10, 10, 3, 3);
        // CutSceneObject auto2 = new CutSceneObject(Images.nuke, 0, 0, 1, 1);
        //
        // Actions: {
        // ret.registerStartupAction(new SpawnAction(auto));
        // ret.registerStartupAction(new SpawnAction(auto2));
        // ret.registerTickAction(new ConditionedMoveAction(new TickCondition(
        // TickConditionType.MODULO_0, 20), auto, 1, 1));
        // ret.registerTickAction(new ConditionedMoveSightAction(
        // new TickCondition(TickConditionType.MODULO_0, 20), 1, 1));
        // ret.registerTickAction(new DebugAction());
        // ret.registerTickAction(new ConditionedExitAction(new TickCondition(
        // TickConditionType.GREATER, 600)));
        // ret.registerTickAction(new ConditionedMoveAction(new TickCondition(
        // TickConditionType.MODULO_0, 10), auto2, 1, 1));
        // }

        CutSceneObject auto = new CutSceneObject(Images.auto_w_WE, 19, 10, 1, 1);

        CutSceneObject straßehorizontal = new CutSceneObject(Images.strasse_EW, 0, 18, 2, 4);
        CutSceneObject wasser = new CutSceneObject(Images.wasser, 300, 0, 1, 1);
        CutSceneObject straßevertikal = new CutSceneObject(Images.strasse_NS, 298, 0, 1, 1);

        CutSceneObject pflaster = new CutSceneObject(Images.pflaster, 0, 0, 1, 1);
        CutSceneObject typ1 = new CutSceneObject(Images.auto_di4_WE, 0, 0, 4, 2);
        CutSceneObject typ2 = new CutSceneObject(Images.auto_di3_WE, 0, 0, 4, 2);
        CutSceneObject typ3 = new CutSceneObject(Images.auto_di2_WE, 0, 0, 4, 2);
        CutSceneObject typ4 = new CutSceneObject(Images.auto_di_WE, 0, 0, 4, 2);

        CutSceneObject typ5 = new CutSceneObject(Images.auto_do_WE, 0, 0, 4, 2);
        CutSceneObject typ6 = new CutSceneObject(Images.auto_do2_WE, 0, 0, 4, 2);
        CutSceneObject typ7 = new CutSceneObject(Images.auto_do3_WE, 0, 0, 4, 2);
        CutSceneObject typ8 = new CutSceneObject(Images.auto_do4_WE, 0, 0, 4, 2);

        CutSceneObject[] baeumer = new CutSceneObject[50];
        CutSceneObject[] baeumel = new CutSceneObject[50];

        CutSceneObject[] strassen = new CutSceneObject[20];
        for (int j = 0; j < strassen.length; j++) {
            strassen[j] = new CutSceneObject(Images.strasse_EW, j * 8, 18, 8, 4);
            one.registerStartupAction(new SpawnAction(strassen[j]));
        }

        // one.registerStartupAction(new ConditionedClearMapAction(null, Raster.rasen));

        for (int i = 0; i < baeumer.length; i++) {
            baeumer[i] = new CutSceneObject(CutSceneUtil.getRanBaum(), i * 3, 17, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumer[i]));

            baeumel[i] = new CutSceneObject(CutSceneUtil.getRanBaum(), i * 3, 22, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumel[i]));
        }

        Actions: {

            // one.registerStartupAction(new SpawnAction(rasen));
            one.registerStartupAction(new SpawnAction(straßehorizontal));

            one.registerStartupAction(new SpawnAction(wasser));
            one.registerStartupAction(new SpawnAction(straßevertikal));
            // one.registerStartupAction(new SpawnAction(pflaster));
            // one.registerStartupAction(new SpawnAction())
            one.registerStartupAction(new SpawnAction(auto));

            one.registerTickAction(new ConditionedMoveAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 10), new TickIntervalCondition(0, 40)), auto, 1, 0));

            one.registerTickAction(new ConditionedMoveSightAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 10), new TickIntervalCondition(40, 100)), 1, 0));

            one.registerTickAction(new DebugAction());

            one.registerTickAction(new ConditionedMoveAction(new TickCondition(TickConditionType.MODULO_0, 10), wasser, -1, 0));

            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(120,122),auto_di4_WE,));
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(122,124),auto_di3_WE,));
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(124,126),auto_di2_WE,))
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(126,128),auto_di1_WE,))
            //
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(128,130),auto_do1_WE, ))
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(132,134),auto_do2_WE))
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(134,136),auto_do3_WE))
            // one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(136,138),auto_do4_WE))

            one.registerTickAction(new ConditionedExitAction(new TickCondition(TickConditionType.GREATER, 600)));
        }
        registerMapping("one", one);
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
