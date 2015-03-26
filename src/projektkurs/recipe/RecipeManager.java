package projektkurs.recipe;

import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.recipe.combination.CombinationRecipeManager;
import projektkurs.recipe.combination.ICombinationRecipeManager;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

public final class RecipeManager {

    public static ICombinationRecipeManager combination;

    @Init(State.INIT)
    public static void init() {
        combination = new CombinationRecipeManager();

        combination.addCombinationRecipe(new ItemStack(Items.item42, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.item42, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.nuke, 16));
    }

    private RecipeManager() {
        // NO-OP
    }

}
