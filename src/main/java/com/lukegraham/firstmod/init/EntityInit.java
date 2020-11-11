package com.lukegraham.firstmod.init;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.entities.KillerSnailEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, FirstMod.MOD_ID);

    public static RegistryObject<EntityType<KillerSnailEntity>> KILLER_SNAIL = ENTITY_TYPES.register(
            "killer_snail", () -> EntityType.Builder.create(KillerSnailEntity::new, EntityClassification.MONSTER)
            .size(0.3F, 0.5F).build(FirstMod.MOD_ID + ":killer_snail"));

    public static void addEntitySpawns(){
        for (Biome biome : ForgeRegistries.BIOMES){
            if (biome.getCategory().equals(Biome.Category.PLAINS)){
                biome.getSpawns(EntityClassification.MONSTER).add(
                        new Biome.SpawnListEntry(KILLER_SNAIL.get(), 100, 1, 4));
            }
        }
    }
}
