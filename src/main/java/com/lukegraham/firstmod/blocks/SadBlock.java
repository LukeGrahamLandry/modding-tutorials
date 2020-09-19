package com.lukegraham.firstmod.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;


public class SadBlock extends Block {
    public SadBlock(Properties properties) {
        // the properties can set lightValue, slipperiness, speedFactor, jumpFactor,
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(handIn);

        if (!worldIn.isRemote && held.getItem() == Items.GUNPOWDER){
            worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.Mode.DESTROY);
            held.shrink(1);
            return ActionResultType.CONSUME;
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!worldIn.isRemote && placer != null){
            placer.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
        }

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.offset(facing)).getBlock();

        if (plant == Blocks.CACTUS){
            return true;
        } else {
            return super.canSustainPlant(state, world, pos, facing, plantable);
        }
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.Mode.DESTROY);

        super.onExplosionDestroy(worldIn, pos, explosionIn);
    }
}
