package ca.lukegrahamlandry.firstmod.entity;

import ca.lukegrahamlandry.firstmod.init.ItemInit;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrow {
    // default constructor, required to register the entity
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    // the constructor used by the ArrowItem
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    // the item stack to give the player when they walk over your arrow stuck in the ground
    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        super.onHitEntity(ray);
        // this, x, y, z, explosionStrength, setsFires, breakMode (NONE, BREAK, DESTROY)
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Explosion.BlockInteraction.BREAK);
    }

    // called each tick while in the ground
    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60){
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Explosion.BlockInteraction.BREAK);
            this.discard();
        }
    }

    // syncs to the client
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
