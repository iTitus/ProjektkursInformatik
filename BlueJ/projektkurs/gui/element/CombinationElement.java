package projektkurs.gui.element;

import projektkurs.inventory.Inventory;
import projektkurs.render.Screen;

public class CombinationElement extends Element {

    private final Inventory combinationInventory;

    public CombinationElement(int posX, int posY, int sizeX, int sizeY, int id, IElementListener listener) {
        super(posX, posY, sizeX, sizeY, id, listener);
        combinationInventory = new Inventory(3);
    }

    @Override
    public void render(Screen screen) {

    }

}
