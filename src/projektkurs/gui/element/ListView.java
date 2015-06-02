package projektkurs.gui.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class ListView<T> extends Element {

    public static final int BORDER_DISTANCE_X = 8;
    public static final int ELEMENT_DISTANCE_Y = 16;

    private final List<T> list;

    public ListView(int posX, int posY, int sizeX, int sizeY, int id, Collection<? extends T> list) {
        super(posX, posY, sizeX, sizeY, id, null);
        this.list = new ArrayList<T>(list);
    }

    @Override
    public void render(Screen screen) {

        int yOffset = ELEMENT_DISTANCE_Y / 2;
        for (T element : list) {
            String string = element.toString();
            Font.drawString(screen, string, posX, posY + yOffset);
            yOffset += Font.getStringHeight(string) + ELEMENT_DISTANCE_Y;
            if (yOffset >= sizeY) {
                break;
            }
        }

        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
    }

}
