package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.dialog.part.DialogPart;
import projektkurs.entity.Entity;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.TextView;
import projektkurs.lib.Integers;
import projektkurs.util.RenderUtil;

/**
 * Das Dialogs-GUI.
 */
public class GuiDialog extends Gui implements IButtonListener {

    /**
     * Aktuell angezeigter DialogPart.
     */
    private final DialogPart dialogPart;
    /**
     * Entity No. 1.
     */
    private final Entity entity1;
    /**
     * Entity No. 2.
     */
    private final Entity entity2;
    /**
     * Eltern-Gui.
     */
    private final Gui parent;
    /**
     * Das Dialog-Anzeigefeld.
     */
    private final TextView text;

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     * @param dialogPart
     *            anzuzeigender DialogPart
     * @param entity1
     *            ein Entity
     * @param entity2
     *            anderer Entity
     */
    public GuiDialog(Gui parent, DialogPart dialogPart, Entity entity1, Entity entity2) {
        this.parent = parent;
        this.dialogPart = dialogPart;
        this.entity1 = entity1;
        this.entity2 = entity2;
        text = new TextView(34, 34, 256, 64, 0, dialogPart.getNextString());
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(text);
        addElement(new Button(34, 34 + 64, 256, 64, 1, this, "button.dialog.next"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        if (dialogPart.hasNextString()) {
            text.setText(dialogPart.getNextString());
            if (!dialogPart.hasNextString()) {
                button.setName("button.dialog.exit");
            }
        } else {
            Main.openGui(parent);
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        // NO-OP
    }

    @Override
    public void render(Graphics2D g) {
        RenderUtil.drawDefaultBackground(g);
        if (dialogPart.isNPCSpeaking()) {
            if (entity2 != null) {
                RenderUtil.drawImage(g, entity2.getImage(), 34 + 256, 34, entity2.getSizeX() * Integers.RASTER_SIZE, entity2.getSizeY() * Integers.RASTER_SIZE);
            }
        } else {
            if (entity1 != null) {
                RenderUtil.drawImage(g, entity1.getImage(), 34 + 256, 34, entity1.getSizeX() * Integers.RASTER_SIZE, entity1.getSizeY() * Integers.RASTER_SIZE);
            }
        }
        super.render(g);
    }
}
