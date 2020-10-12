package com.lukegraham.firstmod.init;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.effects.ExplosiveEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, FirstMod.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, FirstMod.MOD_ID);

    public static final RegistryObject<Effect> EXPLOSIVE_EFFECT = EFFECTS.register("explosive", ExplosiveEffect::new);

    public static final RegistryObject<Potion> EXPLOSIVE_POTION = POTIONS.register("explosive",
            () -> new Potion(new EffectInstance(EXPLOSIVE_EFFECT.get(), 1200, 0)));

    public static final RegistryObject<Potion> LONG_EXPLOSIVE_POTION = POTIONS.register("long_explosive",
            () -> new Potion(new EffectInstance(EXPLOSIVE_EFFECT.get(), 2400, 0)));

    public static final RegistryObject<Potion> STRONG_EXPLOSIVE_POTION = POTIONS.register("strong_explosive",
            () -> new Potion(new EffectInstance(EXPLOSIVE_EFFECT.get(), 600, 1)));

    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, Items.TNT, EXPLOSIVE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(EXPLOSIVE_POTION.get(), Items.REDSTONE, LONG_EXPLOSIVE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(EXPLOSIVE_POTION.get(), Items.GLOWSTONE_DUST, STRONG_EXPLOSIVE_POTION.get()));
    }

    private static class BetterBrewingRecipe implements IBrewingRecipe{
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion outputIn){
            this.bottleInput = bottleInputIn;
            this.itemInput = itemInputIn;
            this.output = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), outputIn);
        }

        // checks the item where the water bottle would go
        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotionFromItem(input).equals(this.bottleInput);
        }

        // checks the item where the nether wort would go
        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        // gets the output potion. Very important to call copy because ItemStacks are mutable
        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)){
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
    }
}
