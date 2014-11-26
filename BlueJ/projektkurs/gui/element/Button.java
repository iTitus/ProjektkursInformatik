package projektkurs.gui.element;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.util.I18n;
import projektkurs.util.RenderUtil;

/**
 * Ein Knopf.
 */
public class Button extends Element {

    /**
     * Ist der Knopf aktiviert.
     */
    protected boolean enabled;
    /**
     * Das Gui, in dem sich dieser Knopf befindet.
     */
    protected IButtonListener gui;
    /**
     * Der Name des Knopfes.
     */
    protected String name;

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     * @param id
     *            Nummer
     * @param gui
     *            Gui/Listener
     * @param name
     *            Name
     */
    public Button(int posX, int posY, int sizeX, int sizeY, int id, IButtonListener gui, String name) {
        super(posX, posY, sizeX, sizeY, id);
        this.name = name;
        this.gui = gui;
        enabled = true;
    }

    /**
     * Der name dieses Knopfes.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Ist dieser Knopf aktiviert.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            gui.onButtonLeftClick(this, e);
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            gui.onButtonRightClick(this, e);
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (enabled) {
            if (isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
                RenderUtil.drawImage(g, Images.buttonHighlight, posX, posY, sizeX, sizeY);
            } else {
                RenderUtil.drawImage(g, Images.button, posX, posY, sizeX, sizeY);
            }
        } else {
            RenderUtil.drawImage(g, Images.buttonDisabled, posX, posY, sizeX, sizeY);
        }

        RenderUtil.drawCenteredStringInRect(g, I18n.getString(name), posX, posY, sizeX, sizeY);
    }

    /**
     * Aktiviert diesen Knopf.
     *
     * @param enabled
     *            true, wenn er aktiviert werden soll; false, wenn er deaktiviert werden soll
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Setzt den Namen dieses Knopfes.
     *
     * @param name
     *            Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
