package projektkurs.cutscene.render;

import projektkurs.render.RenderHelper;

public class CutSceneRenderHelper extends RenderHelper {

    private String stringToDraw;

    public String getStringToDraw() {
        String temp = stringToDraw;
        stringToDraw = null;
        return temp;
    }

    public void setStringToDraw(String stringToDraw) {
        this.stringToDraw = stringToDraw;
    }

    public boolean shouldDraw() {
        return stringToDraw == null;
    }
}
