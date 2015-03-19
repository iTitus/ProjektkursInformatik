package projektkurs.recipe.combination;

import projektkurs.item.ItemStack;

public class StaticCombinationRecipe implements ICombinationRecipe {

    private final ItemStack input1, input2, output;

    public StaticCombinationRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Override
    public boolean areValidInputs(ItemStack stack1, ItemStack stack2) {
        if (stack1 != null && stack2 != null) {
            if (input1.itemAndDamageEquals(stack1) && input2.itemAndDamageEquals(stack2)) {
                return stack1.getStackSize() >= input1.getStackSize() && stack2.getStackSize() >= input2.getStackSize();
            } else if (input2.itemAndDamageEquals(stack1) && input1.itemAndDamageEquals(stack2)) {
                return stack1.getStackSize() >= input2.getStackSize() && stack2.getStackSize() >= input1.getStackSize();
            }
        }
        return false;
    }

    @Override
    public ItemStack getOutput(ItemStack stack1, ItemStack stack2) {
        return output.copy();
    }

}
