package projektkurs.recipe.combination;

import projektkurs.item.ItemStack;

public interface ICombinationRecipe {

    boolean areValidInputs(ItemStack stack1, ItemStack stack2);

    ItemStack getOutput(ItemStack stack1, ItemStack stack2);

}
