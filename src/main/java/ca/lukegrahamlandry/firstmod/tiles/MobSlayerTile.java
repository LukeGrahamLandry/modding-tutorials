package ca.lukegrahamlandry.firstmod.tiles;

import ca.lukegrahamlandry.firstmod.init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MobSlayerTile extends BlockEntity {
    public MobSlayerTile(BlockPos pos, BlockState state) {
        super(TileEntityInit.MOB_SLAYER.get(), pos, state);
    }

    int timer = 0;
    boolean isActive = true;

    public static void tick(Level level, BlockPos pos, BlockState state, MobSlayerTile tile) {
        if (!level.isClientSide() && tile.isActive){
            tile.timer++;
            if (tile.timer > 20){
                tile.timer = 0;

                // only do this once per second
                tile.hurtMobs();
            }
        }
    }

    final int RANGE = 5;
    private void hurtMobs() {
        // define a cube that goes 5 blocks out from our block
        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        // get all the entities within that cube
        List<Entity> entities = this.level.getEntities(null, box);

        for (Entity target : entities){
            // check that the entity is living (ie. not an arrow) and not a player
            if (target instanceof LivingEntity && !(target instanceof Player)){
                // deal damage to the entity
                target.hurt(DamageSource.MAGIC, 2);
            }
        }
    }

    public void toggle(){
        this.isActive = !this.isActive;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putBoolean("active", this.isActive);
        return super.save(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.isActive = nbt.getBoolean("active");
    }
}
