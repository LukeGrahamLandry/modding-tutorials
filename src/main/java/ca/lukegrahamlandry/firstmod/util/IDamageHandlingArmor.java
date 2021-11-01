package ca.lukegrahamlandry.firstmod.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;

// make an armor item that implements this to react to when something wearing the armor is attacked
public interface IDamageHandlingArmor {
    // amount holds the amount of damage to deal after being reduced by armor
    // return the amount of damage to deal instead
    default float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount){
        return amount;
    }
}
