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
import projektkurs.world.Spielfeld;

/**
 * Managt die aktuell laufende CutScene.
 */
public final class CutSceneManager {

    /**
     * Gerade laufende CutScene.
     */
    private static CutScene cutScene;
    /**
     * Das Fenster der gerade laufenden CutScene.
     */
    private static JFrame cutSceneFrame;
    /**
     * CutsceneRender der gerade laufenden CutScene.
     */
    private static CutsceneRender cutSceneRender;
    /**
     * RenderHelper der gerade laufenden CutScene.
     */
    private static RenderHelper cutSceneRenderHelper;
    /**
     * Partielle Ticks.
     */
    private static double delta;
    /**
     * Frames per second.
     */
    private static int fps;

    /**
     * Spielfeld der gerade laufenden CutScene.
     */
    private static Spielfeld map;
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
        return cutScene;
    }

    /**
     * Der aktuelle CutsceneRender der CutScene.
     *
     * @return CutsceneRender
     */
    public static CutsceneRender getCutSceneRender() {
        return cutSceneRender;
    }

    /**
     * Der aktuelle RenderHelper der CutScene.
     *
     * @return RenderHelper
     */
    public static RenderHelper getCutSceneRenderHelper() {
        return cutSceneRenderHelper;
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
        return map;
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
        return cutScene != null;
    }

    /**
     * Führt eine CutScene aus.
     *
     * @param cutSceneToStart
     *            CutScene
     */
    public static void startCutScene(CutScene cutSceneToStart) {

        if (!isRunning()) {
            Logger.info("Starting CutScene");
            Main.pause();
            Main.hide();

            cutScene = cutSceneToStart;
            cutSceneRenderHelper = new RenderHelper();
            cutSceneRender = new CutsceneRender();
            map = Main.getLevel().getMap().copy();

            cutSceneFrame = new JFrame(Strings.NAME + " " + Strings.VERSION + "- CutScene");
            JPanel panel = (JPanel) cutSceneFrame.getContentPane();
            panel.setLayout(null);
            panel.setPreferredSize(cutSceneRender.getCutSceneCanvas().getPreferredSize());
            panel.add(cutSceneRender.getCutSceneCanvas());

            cutSceneFrame.setUndecorated(true);
            cutSceneFrame.setResizable(false);
            cutSceneFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            cutSceneFrame.pack();
            cutSceneFrame.setVisible(true);

            cutSceneRender.initBuffers();

            final double nsPerTick = Integers.NS_PER_SECOND / (double) Integers.UPS;
            fps = 0;
            ups = Integers.UPS;
            delta = 0D;

            int updates = 0;
            int frames = 0;

            long now = 0L;
            long lastTime = System.nanoTime();
            long timer = System.nanoTime();

            while (!cutScene.isFinished()) {
                now = System.nanoTime();
                delta += (now - lastTime) / nsPerTick;
                lastTime = now;
                while (delta >= 1) {
                    updates++;
                    if (cutScene.canUpdate()) {
                        try {
                            cutScene.update();
                        } catch (Throwable t) {
                            Logger.logThrowable("Unable to update the cutscene", t);
                            Main.exit();
                        }
                    }
                    Main.getRenderHelper().addRenderTick();
                    delta--;
                }

                frames++;
                if (cutSceneRender.canUpdate()) {
                    try {
                        cutSceneRender.update();
                    } catch (Throwable t) {
                        Logger.logThrowable("Unable to render the cutscene", t);
                        Main.exit();
                    }
                }

                if (System.nanoTime() - timer >= Integers.NS_PER_SECOND) {
                    timer += Integers.NS_PER_SECOND;
                    ups = updates;
                    fps = frames;
                    updates = 0;
                    frames = 0;
                }

            }

            cutSceneFrame.dispose();
            cutScene = null;
            cutSceneFrame = null;
            cutSceneRenderHelper = null;
            cutSceneRender = null;
            map = null;
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
