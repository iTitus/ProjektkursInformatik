package projektkurs.recipe.combination;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import projektkurs.item.ItemStack;

public class CombinationRecipeManager implements ICombinationRecipeManager {

	private final Set<ICombinationRecipe> recipes;

	public CombinationRecipeManager() {
		recipes = new HashSet<ICombinationRecipe>();
	}

	@Override
	public void addCombinationRecipe(ICombinationRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void addCombinationRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
		recipes.add(new StaticCombinationRecipe(input1, input2, output));
	}

	@Override
	public ICombinationRecipe getRecipe(ItemStack input1, ItemStack input2) {
		if (input1 != null && input2 != null) {
			for (ICombinationRecipe recipe : recipes) {
				if (recipe != null && recipe.areValidInputs(input1, input2)) {
					return recipe;
				}
			}
		}
		return null;
	}

	@Override
	public Set<ICombinationRecipe> getRecipes() {
		return Collections.unmodifiableSet(recipes);
	}

	@Override
	public void removeRecipe(ItemStack input1, ItemStack input2) {
		if (input1 != null && input2 != null) {
			for (Iterator<ICombinationRecipe> iterator = recipes.iterator(); iterator.hasNext(); ) {
				ICombinationRecipe recipe = iterator.next();
				if (recipe != null && recipe.areValidInputs(input1, input2)) {
					iterator.remove();
					return;
				}

			}
		}
	}

}
