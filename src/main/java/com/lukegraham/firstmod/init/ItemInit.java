package com.lukegraham.firstmod.init;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.items.FuelItem;
import com.lukegraham.firstmod.items.TeleportStaff;
import javafx.scene.effect.Effect;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> SMILE = ITEMS.register("smile",
            () -> new Item(new Item.Properties().group(ModItemGroup.instance)));

    // a food that restores 4 hunger points, 2 saturation and give fire resistance for 10 seconds 50% of the time
    public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit",
            () -> new Item(new Item.Properties().group(ModItemGroup.instance)
                    .food(new Food.Builder().hunger(4).saturation(2)
                            .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 200, 0), 0.5F).build())));

    // a fuel that burns in a furnace for twice as long as coal
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties().group(ModItemGroup.instance)));

    // an item that teleports you forward when you right click
    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().group(ModItemGroup.instance).maxDamage(50)));

    // a new creative tab
    public static class ModItemGroup extends ItemGroup {
        public static final ModItemGroup instance = new ModItemGroup(ItemGroup.GROUPS.length, "firstmod");
        private ModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(SMILE.get());
        }
    }

}
