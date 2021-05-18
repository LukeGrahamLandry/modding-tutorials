package ca.lukegrahamlandry.firstmod.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;

// make an armor item that implements this to react to when something wearing the armor is attacked
public interface IDamageHandlingArmor {
    // amount holds the amount of damage to deal after being reduced by armor
    // return the amount of damage to deal instead
    default float onDamaged(LivingEntity entity, EquipmentSlotType slot, DamageSource source, float amount){
        return amount;
    }
}
