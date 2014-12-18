package projektkurs.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.IUpdatable;
import projektkurs.util.RenderUtil;

/**
 * Renderklasse.
 */
public class Render implements IUpdatable {

    /**
     * Das Spiel-Canvas.
     */
    private Canvas canvas;
    /**
     * Das aktuelle Graphics2D-Objekt.
     */
    private Graphics2D g;
    /**
     * Die aktuelle BufferStrategy.
     */
    private BufferStrategy strategy;

    /**
     * Konstruktor.
     */
    public Render() {
        canvas = null;
        strategy = null;
        g = null;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Die aktuelle BufferStrategy.
     *
     * @return BufferStrategy
     */
    public BufferStrategy getBufferStrategy() {
        return strategy;
    }

    /**
     * Gibt das aktuelle Canvas zurück.
     *
     * @return Canvas
     */
    public Canvas getGameCanvas() {
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setIgnoreRepaint(true);
            canvas.setBounds(0, 0, Integers.windowX, Integers.windowY);
            canvas.addKeyListener(Main.getInputManager());
            canvas.addMouseListener(Main.getInputManager());
            canvas.addMouseMotionListener(Main.getInputManager());
            canvas.addMouseWheelListener(Main.getInputManager());
            canvas.setFocusable(true);
            canvas.requestFocus();
            canvas.requestFocusInWindow();
        }
        return canvas;
    }

    /**
     * Initialisiert die BufferStrategy.
     */
    public void initBuffers() {
        canvas.createBufferStrategy(3);
        strategy = canvas.getBufferStrategy();
    }

    /**
     * Updated den Bildschirm.
     */
    @Override
    public void update() {
        if (strategy != null) {

            g = (Graphics2D) strategy.getDrawGraphics();

            g.clearRect(0, 0, Integers.windowX, Integers.windowY);
            g.setColor(Color.BLACK);

            g.drawString("FPS: " + Main.getFPS() + " - UPS: " + Main.getUPS() + " | X: " + Main.getPlayer().getPosX() + " - Y: " + Main.getPlayer().getPosY() + " | Health: " + Main.getPlayer().getHealth() + " / " + Main.getPlayer().getMaxHealth(), Integers.INFO_X, Integers.INFO_Y);

            for (int x = 0; x < Integers.sightX; x++) {
                for (int y = 0; y < Integers.sightY; y++) {
                    int sX = x + Main.getRenderHelper().getSightX();
                    int sY = y + Main.getRenderHelper().getSightY();
                    if (Main.getLevel().getMap().isRasterAt(sX, sY)) {
                        Main.getLevel().getMap().getRasterAt(sX, sY).render(g, sX, sY);
                    } else {
                        RenderUtil.drawDefaultRaster(g, Images.baum, sX, sY);
                    }
                }
            }

            for (Entity e : Main.getLevel().getMap().getEntityList()) {
                if (!e.shouldDeSpawn() && Main.getRenderHelper().isInSight(e)) {
                    e.render(g);
                }
            }

            Main.getGui().render(g);

            g.dispose();
            strategy.show();

        }
    }
}
