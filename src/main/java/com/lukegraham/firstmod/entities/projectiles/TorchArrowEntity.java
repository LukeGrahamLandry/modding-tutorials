package com.lukegraham.firstmod.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class TorchArrowEntity extends ArrowEntity{
    public TorchArrowEntity(World worldIn, LivingEntity shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        super.onHit(raytraceResultIn);
        RayTraceResult.Type hitType = raytraceResultIn.getType();

         // Do something when it hits a block
         if (hitType == RayTraceResult.Type.BLOCK) {
             setToTorch((BlockRayTraceResult)raytraceResultIn);

             // dont stick in the ground as an arrow like a normal one would
             this.remove();
         }
    }

    private void setToTorch(BlockRayTraceResult hit) {
        Direction face = hit.getFace();
        BlockPos pos = hit.getPos().offset(face);
        boolean isAir = this.world.getBlockState(pos).isAir(this.world, pos);

        // if it hits the bottom of a block or somewhere that isnt air (ie water or inside a sign)
        if (face == Direction.DOWN || !isAir){
            ItemEntity torch = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.TORCH));
            world.addEntity(torch);
        }

        // if it hits the top of a block
        else if (face == Direction.UP){
            world.setBlockState(pos, Blocks.TORCH.getDefaultState());
        }

        // if it hits the side of a block
        else {
            BlockState state = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, face);
            world.setBlockState(pos, state);
        }
    }

    // do something when it hits an entity
    @Override
    protected void arrowHit(LivingEntity living) {
        super.arrowHit(living);

        living.setFire(15);
        living.addPotionEffect(new EffectInstance(Effects.GLOWING, 300, 0));
    }
}
