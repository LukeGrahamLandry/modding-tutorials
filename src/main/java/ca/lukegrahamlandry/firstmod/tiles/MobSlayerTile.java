package ca.lukegrahamlandry.firstmod.tiles;

import ca.lukegrahamlandry.firstmod.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MobSlayerTile extends TileEntity implements ITickableTileEntity {
    public MobSlayerTile() {
        super(TileEntityInit.MOB_SLAYER.get());
    }

    int timer = 0;
    boolean isActive = true;

    @Override
    public void tick() {
        if (!this.level.isClientSide() && this.isActive){
            this.timer++;
            if (this.timer > 20){
                this.timer = 0;

                // only do this once per second
                this.hurtMobs();
            }
        }
    }

    final int RANGE = 5;
    private void hurtMobs() {
        // define a cube that goes 5 blocks out from our block
        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AxisAlignedBB box = new AxisAlignedBB(topCorner, bottomCorner);

        // get all the entities within that cube
        List<Entity> entities = this.level.getEntities(null, box);

        for (Entity target : entities){
            // check that the entity is living (ie. not an arrow) and not a player
            if (target instanceof LivingEntity && !(target instanceof PlayerEntity)){
                // deal damage to the entity
                target.hurt(DamageSource.MAGIC, 2);
            }
        }
    }

    public void toggle(){
        this.isActive = !this.isActive;
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putBoolean("active", this.isActive);
        return super.save(nbt);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.isActive = nbt.getBoolean("active");
    }
}
