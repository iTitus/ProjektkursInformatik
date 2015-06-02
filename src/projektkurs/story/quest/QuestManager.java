package projektkurs.story.quest;

import java.util.ArrayList;
import java.util.List;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.util.I18n;
import projektkurs.util.StringUtil;

public class QuestManager implements ISaveable {

    private List<String> finishedQuests, openQuests;

    public QuestManager() {
        finishedQuests = new ArrayList<String>();
        openQuests = new ArrayList<String>();
    }

    @Override
    public void load(SaveData data) {
        int finishedQuestsLength = data.getInteger("finishedQuestsLength");
        finishedQuests = new ArrayList<String>(finishedQuestsLength);
        for (int i = 0; i < finishedQuestsLength; i++) {
            finishedQuests.set(i, data.getString("finishedQuest" + i));
        }
        int openQuestsLength = data.getInteger("openQuestsLength");
        openQuests = new ArrayList<String>(openQuestsLength);
        for (int i = 0; i < openQuestsLength; i++) {
            openQuests.set(i, data.getString("openQuest" + i));
        }
    }

    @Override
    public void write(SaveData data) {
        data.set("finishedQuestsLength", finishedQuests.size());
        for (int i = 0; i < finishedQuests.size(); i++) {
            data.set("finishedQuest" + i, finishedQuests.get(i));
        }
        data.set("openQuestsLength", openQuests.size());
        for (int i = 0; i < openQuests.size(); i++) {
            data.set("openQuest" + i, openQuests.get(i));
        }
    }

    public List<String> getFinishedQuests() {
        List<String> list = new ArrayList<String>();
        for (String finishedQuest : finishedQuests) {
            list.add(I18n.getString("quest." + finishedQuest + ".name"));
        }
        return list;
    }

    public List<String> getOpenQuests() {
        List<String> list = new ArrayList<String>();
        for (String openQuest : openQuests) {
            list.add(I18n.getString("quest." + openQuest + ".name"));
        }
        return list;
    }

    public void openQuest(String quest) {
        if (StringUtil.isNotNullOrEmpty(quest)) {
            openQuests.add(quest);
        }
    }

    public void finishQuest(String quest) {
        if (StringUtil.isNotNullOrEmpty(quest) && openQuests.remove(quest)) {
            finishedQuests.add(0, quest);
        }
    }
}
