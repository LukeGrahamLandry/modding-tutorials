package ca.lukegrahamlandry.firstmod.enchants;

import ca.lukegrahamlandry.firstmod.init.EnchantmentInit;
import ca.lukegrahamlandry.firstmod.util.IDamageHandlingArmor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Random;

public class BridgeEnchantment extends Enchantment {
    public BridgeEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET});
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
        target.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100));
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
