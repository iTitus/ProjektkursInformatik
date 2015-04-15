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
    
        
       combination.addCombinationRecipe(new ItemStack(Items.teddydefault, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.earrings, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.teddyWithEarrings, 1));
    
       combination.addCombinationRecipe(new ItemStack(Items.teddydefault, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.fishnetString, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.teddyWithFishnetString, 1));
       
       combination.addCombinationRecipe(new ItemStack(Items.teddyWithEarrings, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.fishnetString, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.teddyVoodoo, 1));
       
       combination.addCombinationRecipe(new ItemStack(Items.teddyWithFishnetString, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.earrings, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.teddyVoodoo, 1));
       
       combination.addCombinationRecipe(new ItemStack(Items.stoneCatapult, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.stone, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.stoneCatapultwithStone, 1));

       combination.addCombinationRecipe(new ItemStack(Items.fishnet, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.knife, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.fishnetString, 1));

       combination.addCombinationRecipe(new ItemStack(Items.yoyoBroken, 1,Integers.WILDCARD_VALUE ), new ItemStack(Items.chewingGum, 1, Integers.WILDCARD_VALUE), new ItemStack(Items.yoyoFixed, 1));

    }
    
    
    private RecipeManager() {
        // NO-OP
    }

}
