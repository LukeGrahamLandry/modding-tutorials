package com.lukegraham.firstmod.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class KillerSnailEntity extends CreatureEntity {
    public KillerSnailEntity(EntityType<KillerSnailEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // select a target (target used by MeleeAttackGoal)
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 0, true, false, null));

        // move toward and attack the target once within range (calls attackEntityAsMob)
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.35D, false));

        // move around randomly when not doing anything else
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.20D, 50));
    }

    // called when attacking an entity (called by MeleeAttackGoal)
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (entityIn instanceof LivingEntity && !this.world.isRemote()){
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
        }

        return flag;
    }

    // called when this entity takes damage. return before calling the super to negate the damage
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.ON_FIRE && !this.world.isRemote()){
            this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, Explosion.Mode.DESTROY);
            return false;
        }

        if (source == DamageSource.FALL) return false;

        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 10;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15F);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(0.25F);
    }
}
