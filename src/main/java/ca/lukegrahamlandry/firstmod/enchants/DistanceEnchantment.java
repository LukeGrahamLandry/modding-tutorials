package ca.lukegrahamlandry.firstmod.enchants;

import ca.lukegrahamlandry.firstmod.init.ItemInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DistanceEnchantment extends Enchantment {
    static EnchantmentCategory TELEPORT_STAFF_TYPE = EnchantmentCategory.create("teleport_staff", item -> item == ItemInit.TELEPORT_STAFF.get());

    public DistanceEnchantment() {
        super(Rarity.COMMON, TELEPORT_STAFF_TYPE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}
