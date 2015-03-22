package projektkurs.recipe.combination;

import projektkurs.item.ItemStack;

public interface ICombinationRecipe {

    boolean areValidInputs(ItemStack input1, ItemStack input2);

    ItemStack getOutput(ItemStack stack1, ItemStack stack2);

}
