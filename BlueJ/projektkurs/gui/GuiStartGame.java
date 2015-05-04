package projektkurs.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.io.storage.Save;
import projektkurs.io.storage.SaveLoader;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;

public class GuiStartGame extends Gui implements IButtonListener {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public GuiStartGame(Gui parent) {
        super(parent);
    }

    @Override
    public void initGui() {
        Future<List<Save>> future = executorService.submit(new SaveLoader());
        super.initGui();
        addElement(new Button(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 0, this, "button.reload"));
        addElement(new Button(Integers.WINDOW_HUD_X + Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y, 1, this, "button.backToMainMenu"));
        int j = MathUtil.floorDiv(Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.DEFAULT_BUTTON_WIDTH);
        List<Save> list = new ArrayList<Save>();
        try {
            list.addAll(future.get());
        } catch (Exception e) {
            Logger.logThrowable("Unable to load saves", e);
        }
        for (int i = 0; i < list.size(); i++) {
            addElement(new Button(Integers.WINDOW_HUD_X + i % j * Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y + (i / j + 1) * Integers.DEFAULT_BUTTON_HEIGHT, i + 2, this, list.get(i).getName()));
        }
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        switch (button.getID()) {
            case 0:
                Main.openGui(new GuiStartGame(getParent()));
                break;
            case 1:
                Main.openGui(new GuiMainMenu());
                break;
            default:
                break;

        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

}
