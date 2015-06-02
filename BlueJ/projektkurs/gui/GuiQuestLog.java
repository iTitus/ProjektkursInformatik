package projektkurs.gui;

import projektkurs.gui.element.ListView;
import projektkurs.lib.Integers;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.story.quest.QuestManager;
import projektkurs.util.I18n;

public class GuiQuestLog extends Gui {

    private final QuestManager questManager;

    public GuiQuestLog(QuestManager questManager) {
        this.questManager = questManager;
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new ListView<String>(Integers.WINDOW_HUD_X, 2 * Integers.WINDOW_HUD_Y, (Integers.windowX - 2 * Integers.WINDOW_HUD_X) / 2, Integers.windowY - 3 * Integers.WINDOW_HUD_Y, 0, questManager.getFinishedQuests()));
        addElement(new ListView<String>((Integers.windowX - 2 * Integers.WINDOW_HUD_X) / 2 + Integers.WINDOW_HUD_X, 2 * Integers.WINDOW_HUD_Y, (Integers.windowX - 2 * Integers.WINDOW_HUD_X) / 2, Integers.windowY - 3 * Integers.WINDOW_HUD_Y, 1, questManager.getOpenQuests()));
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        Font.drawString(screen, I18n.getString("quest.finishedQuests"), Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y);
        Font.drawString(screen, I18n.getString("quest.openQuests"), (Integers.windowX - 2 * Integers.WINDOW_HUD_X) / 2 + Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y);
    }

}
