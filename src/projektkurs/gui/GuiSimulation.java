package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.gui.element.IPhysicsListener;
import projektkurs.gui.element.PhysicsElement;
import projektkurs.gui.element.PhysicsElementSolid;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

/**
 * Ein Simulations-GUI.
 */
public class GuiSimulation extends Gui implements IPhysicsListener {

    private PhysicsElement box;
    private PhysicsElement ground;

    /**
     * Konstruktor.
     */
    public GuiSimulation() {
    }

    @Override
    public void initGui() {
        super.initGui();
        box = new PhysicsElement(16, 16, 16, 16, 0, this, 10);
        ground = new PhysicsElementSolid(Integers.WINDOW_HUD_X, Integers.windowY - 2 * Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 1, this);
        box.setForce(10, 7.5);
        addElement(box);
        addElement(ground);
    }

    @Override
    public void onCollide(PhysicsElement element, PhysicsElement other) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics2D g) {
        RenderUtil.drawBackground(g, Color.WHITE);
        super.render(g);
    }
}
