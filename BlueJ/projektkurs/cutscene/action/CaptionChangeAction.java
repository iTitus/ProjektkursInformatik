package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;

public class CaptionChangeAction extends Action {

    private final String captionString;

    public CaptionChangeAction(Condition condition, String captionString) {
        super(condition);
        this.captionString = captionString;
    }

    public CaptionChangeAction(String captionString) {
        super();
        this.captionString = captionString;
    }

    @Override
    public void doAction(CutScene cutScene) {
        cutScene.setCaptionString(captionString);
    }

}
