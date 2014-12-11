package projektkurs.cutscene;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import projektkurs.Main;
import projektkurs.cutscene.render.CutsceneRender;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.render.RenderHelper;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.world.Spielfeld;

/**
 * Managt die aktuell laufende CutScene.
 */
public final class CutSceneManager {

    /**
     * Gerade laufende CutScene.
     */
    private static CutScene currCutScene;
    /**
     * CutsceneRender der gerade laufenden CutScene.
     */
    private static CutsceneRender currCutSceneRender;
    /**
     * RenderHelper der gerade laufenden CutScene.
     */
    private static RenderHelper currCutSceneRenderHelper;
    /**
     * Spielfeld der gerade laufenden CutScene.
     */
    private static Spielfeld currSpielfeld;
    /**
     * Das Fenster der gerade laufenden CutScene.
     */
    private static JFrame cutSceneFrame;
    /**
     * Partielle Ticks.
     */
    private static double delta;

    /**
     * Frames per second.
     */
    private static int fps;
    /**
     * Updates per second.
     */
    private static int ups;

    /**
     * Die aktuelle CutScene.
     *
     * @return RenderHelper
     */
    public static CutScene getCutScene() {
        return currCutScene;
    }

    /**
     * Der aktuelle CutsceneRender der CutScene.
     *
     * @return CutsceneRender
     */
    public static CutsceneRender getCutSceneRender() {
        return currCutSceneRender;
    }

    /**
     * Der aktuelle RenderHelper der CutScene.
     *
     * @return RenderHelper
     */
    public static RenderHelper getCutSceneRenderHelper() {
        return currCutSceneRenderHelper;
    }

    /**
     * Die partiellen Ticks.
     *
     * @return delta
     */
    public static double getDelta() {
        return delta;
    }

    /**
     * Die aktuelle FPS (frames per second).
     *
     * @return FPS
     */
    public static int getFPS() {
        return fps;
    }

    /**
     * Das aktuelle Spielfeld der CutScene.
     *
     * @return Spielfeld
     */
    public static Spielfeld getMap() {
        return currSpielfeld;
    }

    /**
     * Die aktuelle UPS (updates per second).
     *
     * @return UPS
     */
    public static int getUPS() {
        return ups;
    }

    /**
     * Läuft gerade eine CutScene.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public static boolean isRunning() {
        return currCutScene != null;
    }

    /**
     * Führt eine CutScene aus.
     *
     * @param cutScene
     *            CutScene
     */
    public static void startCutScene(CutScene cutScene) {

        if (!isRunning()) {
            Logger.info("Starting CutScene");
            Main.pause();
            Main.hide();

            currCutScene = cutScene;
            currCutSceneRenderHelper = new RenderHelper();
            currCutSceneRender = new CutsceneRender();
            currSpielfeld = Main.getLevel().getMap().copy();

            cutSceneFrame = new JFrame(Strings.NAME + " " + Strings.VERSION + "- CutScene");
            JPanel panel = (JPanel) cutSceneFrame.getContentPane();
            panel.setLayout(null);
            panel.setPreferredSize(currCutSceneRender.getCutSceneCanvas().getPreferredSize());
            panel.add(currCutSceneRender.getCutSceneCanvas());

            cutSceneFrame.setUndecorated(true);
            cutSceneFrame.setResizable(false);
            cutSceneFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            cutSceneFrame.pack();
            cutSceneFrame.setVisible(true);

            currCutSceneRender.initBuffers();

            final double nsPerTick = MathUtil.ceilDiv(Integers.NS_PER_SECOND, Integers.UPS);
            fps = 0;
            ups = Integers.UPS;
            int loops = 0, frames = 0;
            delta = 0D;
            long lastTime = System.nanoTime();
            long lastTimer = System.nanoTime();

            while (!currCutScene.isFinished()) {
                long time = System.nanoTime();
                delta += (time - lastTime) / nsPerTick;
                lastTime = time;

                while (delta >= 1) {
                    loops++;
                    if (currCutScene.canUpdate()) {
                        currCutScene.update();
                    }
                    Main.getRenderHelper().addRenderTick();
                    delta--;
                }

                frames++;
                currCutSceneRender.update();

                if (System.nanoTime() - lastTimer >= Integers.NS_PER_SECOND) {
                    lastTimer += Integers.NS_PER_SECOND;
                    ups = loops;
                    fps = frames;
                    frames = 0;
                    loops = 0;
                }

            }

            cutSceneFrame.dispose();
            currCutScene = null;
            cutSceneFrame = null;
            currCutSceneRenderHelper = null;
            currCutSceneRender = null;
            currSpielfeld = null;
            Main.show();
            Main.resume();
            Logger.info("Finished CutScene");
        }

    }

    /**
     * Nicht instanziierbar.
     */
    private CutSceneManager() {
    }

}
