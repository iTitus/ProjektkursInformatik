package projektkurs.gui.element;

import projektkurs.inventory.Inventory;
import projektkurs.recipe.RecipeManager;
import projektkurs.recipe.combination.ICombinationRecipe;

public class CombinationSlotElement extends SlotElement {

	private final SlotElement input1, input2;
	private boolean needsUpdate = true;
	private ICombinationRecipe recipe;

	public CombinationSlotElement(int posX, int posY, int id, ISlotElementListener listener, SlotElement input1, SlotElement input2) {
		super(posX, posY, id, listener, 0, new Inventory(1));
		this.input1 = input1;
		this.input2 = input2;
	}

	@Override
	public boolean canUpdate() {
		return needsUpdate;
	}

	public void onCrafted() {
		inventory.removeItemStack(0);
		input1.decrStackSize(1);
		input2.decrStackSize(1);
		onInputSlotChanged();
	}

	public void onInputSlotChanged() {
		needsUpdate = true;
	}

	@Override
	public void update() {
		recipe = RecipeManager.combination.getRecipe(input1.getItemStack(), input2.getItemStack());
		if (recipe != null) {
			inventory.setItemStackInSlot(0, recipe.getOutput(input1.getItemStack(), input2.getItemStack()));
		}
		needsUpdate = false;
	}

}
