package projektkurs.gui.element;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Font;
import projektkurs.render.Screen;
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
    protected boolean localized;
    /**
     * Der Name des Knopfes.
     */
    protected String name;

    protected int textColor;

    /**
     * Konstruktor.
     * @param posX
     * X-Koordinate
     * @param posY
     * Y-Koordinate
     * @param id
     * Nummer
     * @param listener
     * Listener
     * @param name
     * Name
     */
    public Button(int posX, int posY, int id, IButtonListener listener, String name) {
        this(posX, posY, id, listener, name, true);
    }

    public Button(int posX, int posY, int id, IButtonListener listener, String name, boolean localized) {
        super(posX, posY, Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, id, listener);
        this.name = name;
        enabled = true;
        this.localized = localized;
        textColor = 0xFFFFFF;
    }

    @Override
    public IButtonListener getListener() {
        return (IButtonListener) super.getListener();
    }

    /**
     * Der name dieses Knopfes.
     * @return Name
     */
    public String getName() {
        return name;
    }

    public int getTextColor() {
        return textColor;
    }

    /**
     * Ist dieser Knopf aktiviert.
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLocalized() {
        return localized;
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
    public void render(Screen screen) {
        if (enabled) {
            if (isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
                RenderUtil.drawSprite(screen, Sprites.button_highlight, posX, posY);
            } else {
                RenderUtil.drawSprite(screen, Sprites.button, posX, posY);
            }
        } else {
            RenderUtil.drawSprite(screen, Sprites.button_disabled, posX, posY);
        }
        Font.drawCenteredStringInRect(screen, localized ? I18n.getString(name) : name, posX, posY, sizeX, sizeY, textColor);
    }

    /**
     * Aktiviert diesen Knopf.
     * @param enabled
     * true, wenn er aktiviert werden soll; false, wenn er deaktiviert werden soll
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocalized(boolean localized) {
        this.localized = localized;
    }

    /**
     * Setzt den Namen dieses Knopfes.
     * @param name
     * Name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

}
