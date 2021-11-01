package ca.lukegrahamlandry.firstmod.enchants;

import ca.lukegrahamlandry.firstmod.init.EnchantmentInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BridgeEnchantment extends Enchantment {
    public BridgeEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other) && other != Enchantments.FROST_WALKER;
    }

    // called when you attack something
    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        target.setRemainingFireTicks(40);
    }

    // called when you get attacked by something
    @Override
    public void doPostHurt(LivingEntity target, Entity attacker, int p_151367_3_) {
        target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100));
    }


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BridgeBuildingHandler {
        @SubscribeEvent
        public static void buildBridge(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || !event.player.level.isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BRIDGE.get(), event.player);

            if (level > 0 && event.player.isShiftKeyDown()){
                BlockState state = event.player.level.getBlockState(event.player.blockPosition().below());
                if (!state.isAir()) return;
                event.player.level.setBlockAndUpdate(event.player.blockPosition().below(), Blocks.SLIME_BLOCK.defaultBlockState());
            }
        }
    }
}
