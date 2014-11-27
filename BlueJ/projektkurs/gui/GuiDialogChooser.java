package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.dialog.Dialog;
import projektkurs.dialog.part.DialogPart;
import projektkurs.entity.Entity;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.DialogButton;
import projektkurs.gui.element.IButtonListener;
import projektkurs.util.RenderUtil;

/**
 * Das Dialog GUI.
 */
public class GuiDialogChooser extends Gui implements IButtonListener {

    /**
     * Aktueller Dialog.
     */
    private final Dialog dialog;

    /**
     * Entity No. 1.
     */
    private final Entity entity1;

    /**
     * Entity No. 2.
     */
    private final Entity entity2;

    /**
     * Konstruktor.
     *
     * @param dialog
     *            Dialog
     * @param entity
     *            anderer Entity
     */
    public GuiDialogChooser(Dialog dialog, Entity entity) {
        this(dialog, Main.getPlayer(), entity);
    }

    /**
     * Konstruktor.
     *
     * @param dialog
     *            Dialog
     * @param entity1
     *            ein Entity
     * @param entity2
     *            anderer Entity
     */
    public GuiDialogChooser(Dialog dialog, Entity entity1, Entity entity2) {
        this.dialog = dialog;
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    @Override
    public void initGui() {
        super.initGui();
        int id = 0;
        for (DialogPart shown : dialog.getShownParts()) {
            addElement(new DialogButton(34, 34 + id * 64, 256, 64, id++, this, shown));
        }
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        if (button instanceof DialogButton) {
            DialogPart part = ((DialogButton) button).getDialogPart();
            part.setActivated(false);
            Main.openGui(new GuiDialog(this, part, entity1, entity2));
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void render(Graphics2D g) {
        RenderUtil.drawDefaultBackground(g);
        super.render(g);
    }
}
