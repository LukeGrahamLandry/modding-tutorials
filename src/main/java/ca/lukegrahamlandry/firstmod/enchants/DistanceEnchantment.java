package ca.lukegrahamlandry.firstmod.enchants;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

// doesnt actually do anything but can be applied to our own custom item
public class DistanceEnchantment extends Enchantment {
    static EnchantmentType TELEPORT_STAFF_TYPE = EnchantmentType.create("teleport_staff", item -> item == ItemInit.TELEPORT_STAFF.get());

    public DistanceEnchantment() {
        super(Rarity.COMMON, TELEPORT_STAFF_TYPE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }
}
