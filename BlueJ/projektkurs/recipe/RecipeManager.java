package projektkurs.recipe;

import projektkurs.recipe.combination.CombinationRecipeManager;
import projektkurs.recipe.combination.ICombinationRecipeManager;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

public final class RecipeManager {

    public static ICombinationRecipeManager combination;

    @Init(State.PRE)
    public static void init() {
        combination = new CombinationRecipeManager();
    }

    private RecipeManager() {
        // NO-OP
    }

}
