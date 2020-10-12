package com.lukegraham.firstmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.world.Explosion;

public class ExplosiveEffect extends Effect {
    public ExplosiveEffect() {
        super(EffectType.HARMFUL, 7033103);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
        entity.getEntityWorld().createExplosion(
                null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), amplifier+1, Explosion.Mode.NONE
        );  // create an explosion that doesn't damage blocks
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 100 == 0;  // preform effect once every 5 seconds
    }
}