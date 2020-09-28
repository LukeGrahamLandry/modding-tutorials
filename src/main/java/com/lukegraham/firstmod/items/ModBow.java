package com.lukegraham.firstmod.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ModBow extends BowItem {
    public ModBow(Properties builder) {
        super(builder);
    }

    public void onPlayerStoppedUsing(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean hasInfinity = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bowStack) > 0;
            ItemStack ammoStack = playerentity.findAmmo(bowStack);

            int timeDrawn = this.getUseDuration(bowStack) - timeLeft;
            timeDrawn = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, worldIn, playerentity, timeDrawn, !ammoStack.isEmpty() || hasInfinity);
            if (timeDrawn < 0) return;

            if (!ammoStack.isEmpty() || hasInfinity) {
                boolean isTippedArrow = ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW;

                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(Items.ARROW);
                }

                float velocity = getArrowVelocity(timeDrawn);
                if (!((double)velocity < 0.1D)) {
                    if (!worldIn.isRemote) {
                        AbstractArrowEntity arrowEntity = createArrow(worldIn, ammoStack, playerentity);

                        arrowEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);

                        if (velocity == 1.0F) arrowEntity.setIsCritical(true);

                        double damage = getArrowDamage(bowStack, arrowEntity);
                        arrowEntity.setDamage(damage);

                        int knockback = getArrowKnockback(bowStack, arrowEntity);
                        arrowEntity.setKnockbackStrength(knockback);

                        // apply flame enchant
                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0) {
                            arrowEntity.setFire(100);
                        }

                        // reduce bow durability
                        bowStack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });

                        // set if arrow can be picked up from ground
                        if (hasInfinity && !isTippedArrow) {
                            arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        // actually make the arrow entity exist in the world
                        worldIn.addEntity(arrowEntity);
                    }

                    // sound
                    worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

                    // use an arrow
                    boolean shouldConsumeArrow = !hasInfinity || isTippedArrow;
                    if (shouldConsumeArrow) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            playerentity.inventory.deleteStack(ammoStack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    // override to use a custom arrow entity
    protected AbstractArrowEntity createArrow(World worldIn, ItemStack ammoStack, PlayerEntity playerentity) {
        ArrowItem arrowitem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
        return arrowitem.createArrow(worldIn, ammoStack, playerentity);
    }

    protected double getArrowDamage(ItemStack bowStack, AbstractArrowEntity arrowEntity) {
        int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);

        if (powerLevel > 0) return arrowEntity.getDamage() + (double)powerLevel * 0.5D + 0.5D;
        else return arrowEntity.getDamage();
    }

    protected int getArrowKnockback(ItemStack bowStack, AbstractArrowEntity arrowEntity) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);
    }

    // Override to change what it uses as ammo
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return (ammoStack) -> {
            return ammoStack.getItem().isIn(ItemTags.ARROWS);
        };
    }
}
