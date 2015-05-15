package projektkurs.recipe.combination;

import java.util.Set;

import projektkurs.item.ItemStack;

public interface ICombinationRecipeManager {

    void addCombinationRecipe(ICombinationRecipe recipe);

    void addCombinationRecipe(ItemStack input1, ItemStack input2, ItemStack output);

    ICombinationRecipe getRecipe(ItemStack input1, ItemStack input2);

    Set<ICombinationRecipe> getRecipes();

    void removeRecipe(ItemStack input1, ItemStack input2);

}
