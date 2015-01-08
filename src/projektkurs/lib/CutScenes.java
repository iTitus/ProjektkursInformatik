package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedClearMapAction;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.ConditionedMoveAction;
import projektkurs.cutscene.action.ConditionedMoveSightAction;
import projektkurs.cutscene.action.ConditionedRasterChangeAction;
import projektkurs.cutscene.action.ConditionedSpawnAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.action.SpawnAction;
import projektkurs.cutscene.condition.AreaCondition;
import projektkurs.cutscene.condition.CombinedAndCondition;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.condition.TickIntervalCondition;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.CutSceneUtil;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle CutScenes.
 */
public final class CutScenes {

    /**
     * Die Mappings.
     */
    public static final HashMap<String, CutScene> MAPPINGS = new HashMap<String, CutScene>();
    /**
     * CutScene No. 1
     */
    public static CutScene one;

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
        one = new CutScene("one");
        /*
         * CutSceneObject auto_x = new CutSceneObject(Images.item42, 10, 10, 3, 3); CutSceneObject figur = new CutSceneObject(Images.key, 5, 5, 1, 1); CutSceneObject hafen = new CutSceneObject(Images.baum, 50, 7, 9, 9); one.registerStartupAction(new SpawnAction(auto_x)); one.registerStartupAction(new
         * SpawnAction(figur)); one.registerStartupAction(new SpawnAction(hafen)); one.registerTickAction(new ConditionedMoveAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 20), new TickIntervalCondition(0, 79)), auto_x, 1, 0)); one.registerTickAction(new ConditionedMoveSightAction(new
         * CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 20), new TickIntervalCondition(80, 200)), 1, 0)); one.registerTickAction(new DebugAction()); one.registerTickAction(new ConditionedExitAction(new TickCondition(TickConditionType.GREATER, 600)));
         */

        one.registerStartupAction(new ConditionedClearMapAction(null, Raster.rasen));

        CutSceneObject strasseHorizontal = new CutSceneObject(Images.strasse_EW, 0, 8, 2, 4);

        CutSceneObject strasseVertikal = new CutSceneObject(Images.strasse_NS, 45, 0, 4, 8);
        CutSceneObject strasseVertikal2 = new CutSceneObject(Images.strasse_NS, 45, 8, 4, 8);
        CutSceneObject strasseVertikal3 = new CutSceneObject(Images.strasse_NS, 45, 16, 4, 8);
        CutSceneObject kreuzungT = new CutSceneObject(Images.kreuzungT_EW, 44, 7, 6, 6);
        CutSceneObject schranke1 = new CutSceneObject(Images.schranke_NS, 49, 7, 2, 3);
        CutSceneObject schranke2 = new CutSceneObject(Images.schranke_SN, 43, 10, 2, 3);

        CutSceneObject[] plaster = new CutSceneObject[5];
        CutSceneObject[] plaster2 = new CutSceneObject[5];
        CutSceneObject[] plaster3 = new CutSceneObject[5];

        for (int k = 0; k < plaster.length; k++) {
            plaster[k] = new CutSceneObject(Images.pflaster2, 49, k * 2 + 5, 2, 2);
            plaster2[k] = new CutSceneObject(Images.pflaster2, 51, k * 2 + 5, 2, 2);
            plaster3[k] = new CutSceneObject(Images.pflaster2, 53, k * 2 + 5, 2, 2);
            one.registerStartupAction(new SpawnAction(plaster[k]));
            one.registerStartupAction(new SpawnAction(plaster2[k]));
            one.registerStartupAction(new SpawnAction(plaster3[k]));
        }

        CutSceneObject auto = new CutSceneObject(Images.auto_w_WE, 9, 10, 4, 2);

        CutSceneObject typ1 = new CutSceneObject(Images.auto_di4_WE, 40, 0, 4, 2);
        CutSceneObject typ2 = new CutSceneObject(Images.auto_di3_WE, 40, 0, 4, 2);
        CutSceneObject typ3 = new CutSceneObject(Images.auto_di2_WE, 40, 0, 4, 2);
        CutSceneObject typ4 = new CutSceneObject(Images.auto_di_WE, 40, 0, 4, 2);

        CutSceneObject typ5 = new CutSceneObject(Images.auto_do_WE, 40, 0, 4, 2);
        CutSceneObject typ6 = new CutSceneObject(Images.auto_do2_WE, 40, 0, 4, 2);
        CutSceneObject typ7 = new CutSceneObject(Images.auto_do3_WE, 40, 0, 4, 2);
        CutSceneObject typ8 = new CutSceneObject(Images.auto_do4_WE, 40, 0, 4, 2);

        CutSceneObject[] baeumer = new CutSceneObject[16];
        CutSceneObject[] baeumel = new CutSceneObject[16];

        CutSceneObject[] strassen = new CutSceneObject[6];

        for (int j = 0; j < strassen.length; j++) {
            strassen[j] = new CutSceneObject(Images.strasse_EW, j * 8, 8, 8, 4);
            one.registerStartupAction(new SpawnAction(strassen[j]));
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 21; j++) {
                one.registerStartupAction(new ConditionedRasterChangeAction(55 + i, 0 + j, null, Raster.water));
            }
        }

        // one.registerStartupAction(new ConditionedClearMapAction(null, Raster.rasen));

        for (int i = 0; i < baeumer.length; i++) {
            baeumer[i] = new CutSceneObject(CutSceneUtil.getRandomTree(), i * 3, 6, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumer[i]));

            baeumel[i] = new CutSceneObject(CutSceneUtil.getRandomTree(), i * 3, 12, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumel[i]));
        }

        CutSceneObject[] baeumehafen1 = new CutSceneObject[8];
        CutSceneObject[] baeumehafen2 = new CutSceneObject[8];

        for (int l = 0; l < baeumehafen1.length; l++) {
            baeumehafen1[l] = new CutSceneObject(CutSceneUtil.getRandomTree(), 54, l + 1, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumehafen1[l]));

            baeumehafen2[l] = new CutSceneObject(CutSceneUtil.getRandomTree(), 54, l + 12, 2, 2);
            one.registerStartupAction(new SpawnAction(baeumehafen2[l]));
        }

        // one.registerStartupAction(new SpawnAction(rasen));
        one.registerStartupAction(new SpawnAction(strasseHorizontal));

        one.registerStartupAction(new SpawnAction(strasseVertikal));
        one.registerStartupAction(new SpawnAction(strasseVertikal2));
        one.registerStartupAction(new SpawnAction(strasseVertikal3));
        one.registerStartupAction(new SpawnAction(kreuzungT));

        one.registerStartupAction(new SpawnAction(schranke1));
        one.registerStartupAction(new SpawnAction(schranke2));

        one.registerStartupAction(new SpawnAction(auto));

        one.registerTickAction(new ConditionedMoveAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 10), new AreaCondition(auto, 0, 0, 40, 20)), auto, 1, 0));

        one.registerTickAction(new ConditionedMoveSightAction(new CombinedAndCondition(new TickCondition(TickConditionType.MODULO_0, 10), new AreaCondition(auto, 0, 0, 40, 20)), 1, 0));

        one.registerTickAction(new DebugAction());

        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(120, 122), typ1));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(122, 124), typ2));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(124, 126), typ3));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(126, 128), typ4));

        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(128, 130), typ5));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(132, 134), typ6));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(134, 136), typ7));
        one.registerTickAction(new ConditionedSpawnAction(new TickIntervalCondition(136, 138), typ8));

        one.registerTickAction(new ConditionedExitAction(new TickCondition(TickConditionType.GREATER, 600)));

        registerMapping(one);
    }

    /**
     * Registriert ein Mapping.
     *
     * @param c
     *            CutScene
     */
    private static void registerMapping(CutScene c) {
        if (c != null && !MAPPINGS.containsKey(c.getName())) {
            MAPPINGS.put(c.getName(), c);
        } else {
            Logger.warn("Unable to register CutScene", c);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private CutScenes() {
    }

}
