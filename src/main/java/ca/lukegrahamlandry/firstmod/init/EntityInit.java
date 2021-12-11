package ca.lukegrahamlandry.firstmod.init;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.entity.ExplosiveArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, FirstModMain.MOD_ID);

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITY_TYPES.register("torch_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("torch_arrow"));
}


