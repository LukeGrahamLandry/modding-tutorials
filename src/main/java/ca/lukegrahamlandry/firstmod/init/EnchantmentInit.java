package ca.lukegrahamlandry.firstmod.init;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.enchants.BridgeEnchantment;
import ca.lukegrahamlandry.firstmod.enchants.DistanceEnchantment;
import ca.lukegrahamlandry.firstmod.items.FlamingArmorItem;
import ca.lukegrahamlandry.firstmod.items.FuelItem;
import ca.lukegrahamlandry.firstmod.items.TeleportStaff;
import ca.lukegrahamlandry.firstmod.util.ModArmorMaterial;
import ca.lukegrahamlandry.firstmod.util.ModItemTier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FirstModMain.MOD_ID);

    public static final RegistryObject<Enchantment> BRIDGE = ENCHANTMENTS.register("bridge", BridgeEnchantment::new);

    public static final RegistryObject<Enchantment> DISTANCE = ENCHANTMENTS.register("distance", DistanceEnchantment::new);
}


