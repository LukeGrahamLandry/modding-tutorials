package ca.lukegrahamlandry.firstmod.init;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.tiles.MobSlayerTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, FirstModMain.MOD_ID);

    public static final RegistryObject<TileEntityType<MobSlayerTile>> MOB_SLAYER
            = TILE_ENTITY_TYPES.register("mob_slayer",
            () -> TileEntityType.Builder.of(MobSlayerTile::new, BlockInit.MOB_SLAYER.get()).build(null));

}

