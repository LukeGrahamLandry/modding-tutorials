package ca.lukegrahamlandry.firstmod.entity;

import ca.lukegrahamlandry.firstmod.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrowEntity {
    // default constructor, required to register the entity
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, World world) {
        super(entityType, x, y, z, world);
    }

    // the constructor used by the ArrowItem
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, World world) {
        super(entityType, shooter, world);
    }

    // the item stack to give the player when they walk over your arrow stuck in the ground
    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult ray) {
        super.onHitEntity(ray);
        // this, x, y, z, explosionStrength, setsFires, breakMode (NONE, BREAK, DESTROY)
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Explosion.Mode.BREAK);
    }

    // called each tick while in the ground
    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60){
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Explosion.Mode.BREAK);
            this.remove();
        }
    }

    // syncs to the client
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
