package ca.lukegrahamlandry.firstmod.init;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.items.ExplosiveArrowItem;
import ca.lukegrahamlandry.firstmod.items.FlamingArmorItem;
import ca.lukegrahamlandry.firstmod.items.FuelItem;
import ca.lukegrahamlandry.firstmod.items.TeleportStaff;
import ca.lukegrahamlandry.firstmod.util.ModArmorMaterial;
import ca.lukegrahamlandry.firstmod.util.ModItemTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstModMain.MOD_ID);

    public static final RegistryObject<Item> SMILE = ITEMS.register("smile",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance).food(
                    new FoodProperties.Builder().nutrition(4).saturationMod(2)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 0.5F)
                            .build())));

    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties().tab(ModCreativeTab.instance), 3200));

    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().tab(ModCreativeTab.instance).durability(50)));

    // basic tool set
    public static final RegistryObject<Item> PINK_SWORD = ITEMS.register("pink_sword",
            () -> new SwordItem(ModItemTier.PINK, 3, -2.4F, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_PICKAXE = ITEMS.register("pink_pickaxe",
            () -> new PickaxeItem(ModItemTier.PINK,1, -1.0F, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_AXE = ITEMS.register("pink_axe",
            () -> new AxeItem(ModItemTier.PINK, 5, -3.4F, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_SHOVEL = ITEMS.register("pink_shovel",
            () -> new ShovelItem(ModItemTier.PINK, 1, -1.0F, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_HOE = ITEMS.register("pink_hoe",
            () -> new HoeItem(ModItemTier.PINK, -4, -1.0F, new Item.Properties().tab(ModCreativeTab.instance)));

    // a basic armor set
    public static final RegistryObject<Item> PINK_HELMET = ITEMS.register("pink_helmet",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_CHESTPLATE = ITEMS.register("pink_chestplate",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_LEGGINGS = ITEMS.register("pink_leggings",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> PINK_BOOTS = ITEMS.register("pink_boots",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeTab.instance)));

    // special armor
    public static final RegistryObject<Item> FLAMING_CHESTPLATE = ITEMS.register("flaming_chestplate",
            () -> new FlamingArmorItem(ModArmorMaterial.PINK, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTab.instance)));

    // custom arrow
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties().tab(ModCreativeTab.instance)));


    public static class ModCreativeTab extends CreativeModeTab {
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "firstmod");
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SMILE.get());
        }
    }
}
