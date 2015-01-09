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
     * @param listener
     *            Listener
     * @param name
     *            Name
     */
    public Button(int posX, int posY, int sizeX, int sizeY, int id, IButtonListener listener, String name) {
        super(posX, posY, sizeX, sizeY, id, listener);
        this.name = name;
        enabled = true;
    }

    @Override
    public IButtonListener getListener() {
        return (IButtonListener) super.getListener();
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
        if (enabled && isInside(x, y)) {
            getListener().onButtonLeftClick(this, e);
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        if (enabled && isInside(x, y)) {
            getListener().onButtonRightClick(this, e);
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
