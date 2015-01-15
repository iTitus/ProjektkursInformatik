package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.TextView;
import projektkurs.render.Screen;
import projektkurs.util.I18n;
import projektkurs.util.RenderUtil;

/**
 * Das Sprachauswahl-GUI.
 */
public class GuiLangChooser extends Gui implements IButtonListener {

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     */
    public GuiLangChooser(Gui parent) {
        super(parent);
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new TextView(34, 34, 256, 64, 0, "info.lang.current", I18n.getLocale()));
        Button b;
        for (int i = 0; i < I18n.getSupportedLocales().length; i++) {
            b = new Button(34, 34 + (i + 1) * 64, 256, 64, i + 1, this, I18n.getSupportedLocales()[i].getUnlocalizedName());
            if (I18n.getSupportedLocales()[i].equals(I18n.getLocale())) {
                b.setEnabled(false);
            }
            addElement(b);
        }
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        I18n.changeLocale(I18n.getSupportedLocales()[button.getID() - 1]);
        Main.openGui(getParent());
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }


}
