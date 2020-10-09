package com.lukegraham.firstmod.init;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.items.FuelItem;
import com.lukegraham.firstmod.items.TeleportStaff;
import com.lukegraham.firstmod.items.TorchBow;
import com.lukegraham.firstmod.util.ModArmorMaterial;
import com.lukegraham.firstmod.util.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    // a basic item
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

    // an item that teleports you forward when you right click. Uses durability, has a tooltip and cooldown
    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().group(ModItemGroup.instance).maxDamage(50)));

    // basic tool set
    public static final RegistryObject<Item> PINK_SWORD = ITEMS.register("pink_sword",
            () -> new SwordItem(ModItemTier.PINK, 3, -2.4F, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_PICKAXE = ITEMS.register("pink_pickaxe",
            () -> new PickaxeItem(ModItemTier.PINK,1, -1.0F, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_AXE = ITEMS.register("pink_axe",
            () -> new AxeItem(ModItemTier.PINK, 6, -3.4F, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_SHOVEL = ITEMS.register("pink_shovel",
            () -> new ShovelItem(ModItemTier.PINK, 1, -1.0F, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_HOE = ITEMS.register("pink_hoe",
            () -> new HoeItem(ModItemTier.PINK, -1.0F, new Item.Properties().group(ModItemGroup.instance)));

    // a basic armor set
    public static final RegistryObject<Item> PINK_HELMET = ITEMS.register("pink_helmet",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_CHESTPLATE = ITEMS.register("pink_chestplate",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_LEGGINGS = ITEMS.register("pink_leggings",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<Item> PINK_BOOTS = ITEMS.register("pink_boots",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroup.instance)));

    // torch bow
    public static final RegistryObject<Item> TORCH_BOW = ITEMS.register("torch_bow",
            () -> new TorchBow(new Item.Properties().group(ModItemGroup.instance).maxDamage(500)));

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
