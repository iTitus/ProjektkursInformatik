package projektkurs.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.IUpdatable;

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

            if (Main.getLevel() != null) {
                Main.getLevel().render(g);
            }

            g.setColor(Color.BLACK);
            Main.getGui().render(g);

            g.drawString("FPS: " + Main.getFPS() + " - UPS: " + Main.getUPS() + (Main.getLevel() != null && Main.getPlayer() != null ? " | X: " + Main.getPlayer().getPosX() + " - Y: " + Main.getPlayer().getPosY() + " | Health: " + Main.getPlayer().getHealth() + " / " + Main.getPlayer().getMaxHealth() : ""),
                    Integers.INFO_X, Integers.INFO_Y);

            g.dispose();
            strategy.show();

        }
    }
}
