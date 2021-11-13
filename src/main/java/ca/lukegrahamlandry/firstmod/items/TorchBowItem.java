package ca.lukegrahamlandry.firstmod.items;

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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class TorchBowItem extends BowItem {
    public TorchBowItem(Properties props) {
        super(props);
    }

    public void releaseUsing(ItemStack bowStack, World world, LivingEntity shooter, int timeRemaining) {
        if (shooter instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)shooter;
            // find the ammo stack
            boolean hasInfinity = player.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0;
            ItemStack ammoStack = player.getProjectile(bowStack);
            if (hasInfinity && ammoStack.isEmpty()) {
                ammoStack = new ItemStack(Items.ARROW);
            }

            int drawTime = this.getUseDuration(bowStack) - timeRemaining;
            drawTime = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, world, player, drawTime, !ammoStack.isEmpty() || hasInfinity);
            if (drawTime < 0) return;

            if (!ammoStack.isEmpty()) {
                float force = getPowerForTime(drawTime);
                if (!((double)force < 0.1D)) {
                    boolean canApplyInfinity = player.abilities.instabuild || (ammoStack.getItem() instanceof ArrowItem && ((ArrowItem)ammoStack.getItem()).isInfinite(ammoStack, bowStack, player));
                    if (!world.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
                        AbstractArrowEntity arrow = arrowitem.createArrow(world, ammoStack, player);
                        arrow = customArrow(arrow);
                        arrow.shootFromRotation(player, player.xRot, player.yRot, 0.0F, force * 3.0F, 1.0F);
                        if (force == 1.0F) {
                            arrow.setCritArrow(true);
                        }

                        double damage = getArrowDamage(bowStack, arrow);
                        arrow.setBaseDamage(damage);

                        int knockback = getArrowKnockback(bowStack, arrow);
                        arrow.setKnockback(knockback);

                        int fireTime = getArrowFireTime(bowStack, arrow);

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
                            arrow.setSecondsOnFire(100);
                        }

                        bowStack.hurtAndBreak(1, player, (p_220009_1_) -> {
                            p_220009_1_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (canApplyInfinity || player.abilities.instabuild && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW)) {
                            arrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        world.addFreshEntity(arrow);
                    }

                    world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + force * 0.5F);
                    if (!canApplyInfinity && !player.abilities.instabuild) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            player.inventory.removeItem(ammoStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    protected double getArrowDamage(ItemStack bowStack, AbstractArrowEntity arrow) {
        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
        if (powerLevel > 0) {
            return arrow.getBaseDamage() + (double) powerLevel * 0.5D + 0.5D;
        } else {
            return arrow.getBaseDamage();
        }
    }

    protected int getArrowKnockback(ItemStack bowStack, AbstractArrowEntity arrow) {
        return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
    }
}
