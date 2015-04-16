package projektkurs.gui.element;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.util.IGuiTooltipProvider;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.IUpdatable;

/**
 * Ein GUI-Element.
 */
public abstract class Element implements IHasPositionAndSize<Integer, Integer>, IUpdatable, IGuiTooltipProvider {

    /**
     * Der Listener dieses Elements.
     */
    private final IElementListener listener;
    /**
     * Nummer.
     */
    protected final int id;
    /**
     * X-Position.
     */
    protected int posX;
    /**
     * Y-Position.
     */
    protected int posY;
    /**
     * Breite.
     */
    protected int sizeX;

    /**
     * Hoehe.
     */
    protected int sizeY;

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
     *            Hoehe
     * @param id
     *            Nummer
     * @param listener
     *            Listener
     */
    public Element(int posX, int posY, int sizeX, int sizeY, int id, IElementListener listener) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.id = id;
        this.listener = listener;
    }

    @Override
    public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
        // NO-OP
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    /**
     * Die Nummer dieses Elements.
     *
     * @return Nummmer
     */
    public int getID() {
        return id;
    }

    /**
     * Der Listener dieses Elements.
     *
     * @return Listener.
     */
    public IElementListener getListener() {
        return listener;
    }

    @Override
    public Integer getPosX() {
        return posX;
    }

    @Override
    public Integer getPosY() {
        return posY;
    }

    @Override
    public Integer getSizeX() {
        return sizeX;
    }

    @Override
    public Integer getSizeY() {
        return sizeY;
    }

    /**
     * Ist ein anderes Element innerhalb von diesem Element.
     *
     * @param e
     *            anderes Element
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(Element e) {
        return isInside(e.posX, e.posY, e.sizeX, e.sizeY);
    }

    /**
     * Ist der gegebene Punkt innerhalb von diesem Element.
     *
     * @param posX
     *            X-Koordinate des Punkts
     * @param posY
     *            Y-Koordinate des Punkts
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(int posX, int posY) {
        return isInside(posX, posY, 1, 1);
    }

    /**
     * Ist das gegebene Rechteck innerhalb von diesem Element.
     *
     * @param posX
     *            X-Koordinate der oberen linken Ecke des Rechtecks
     * @param posY
     *            Y-Koordinate der oberen linken Ecke des Rechtecks
     * @param sizeX
     *            Breite des Rechtecks
     * @param sizeY
     *            Hoehe des Rechtecks
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(int posX, int posY, int sizeX, int sizeY) {
        return Math.max(posX, this.posX) < Math.min(posX + sizeX, this.posX + this.sizeX) && Math.max(posY, this.posY) < Math.min(posY + sizeY, this.posY + this.sizeY);
    }

    /**
     * Wird aufgerufen, wenn eine Taste gedrueckt wird.
     *
     * @param keyChar
     *            gedrueckter Buchstabe
     * @param e
     *            KeyEvent
     */
    public void onKeyTyped(char keyChar, KeyEvent e) {
        // NO-OP
    }

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaste auf den Bildschirm geklickt wird.
     *
     * @param x
     *            X-Bildschirmkoordinate
     * @param y
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onLeftClick(int x, int y, MouseEvent e) {
        // NO-OP
    }

    /**
     * Wird ausgefuehrt, wenn das Mausrad bewegt im Bildschirm bewegt wird.
     *
     * @param by
     *            Bewegung des Mausrads
     * @param e
     *            MouseWheelEvent
     */
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        // NO-OP
    }

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaste auf den Bildschirm geklickt wird.
     *
     * @param x
     *            X-Bildschirmkoordinate
     * @param y
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onRightClick(int x, int y, MouseEvent e) {
        // NO-OP
    }

    /**
     * Rendert dieses Element.
     *
     * @param screen
     *            Screen
     */
    public abstract void render(Screen screen);

    @Override
    public void setPosition(Integer x, Integer y) {
        posX = x;
        posY = y;
    }

    @Override
    public void setSize(Integer sizeX, Integer sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void update() {
        // NO-OP
    }

}
