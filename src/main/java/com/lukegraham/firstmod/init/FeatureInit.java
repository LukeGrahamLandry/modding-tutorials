package com.lukegraham.firstmod.init;

import java.util.Locale;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.world.structures.HousePieces;
import com.lukegraham.firstmod.world.structures.HouseStructure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FeatureInit
{
    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Structure<NoFeatureConfig> HOUSE = new HouseStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType HOUSE_PIECE = HousePieces.Piece::new;

    /**
     * This method will be called by Forge when it is time for the mod to register features.
     */
    @SubscribeEvent
    public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event)
    {
        //registers the structures/features.
        //If you don't do this, you'll crash.
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        /* Registers the structure itself and sets what its path is. In this case,
         * the structure will have the resourcelocation of firstmod:house .
         *
         * It is always a good idea to register your regular features too so that other mods
         * can use them too directly from the Forge Registry. It great for mod compatibility.
         */
        HOUSE.setRegistryName(new ResourceLocation(FirstMod.MOD_ID, "house"));  // the string here must be the same as in HouseStructure.getStructureName
        registry.register(HOUSE);
        Registry.register(Registry.STRUCTURE_PIECE, "house", HOUSE_PIECE);

        FirstMod.LOGGER.log(Level.INFO, "features/structures registered.");
    }

    public static void addToBiomes(){
        //Add our structure to all biomes including other modded biomes
        //
        //You can filter to certain biomes based on stuff like temperature, scale, precipitation, mod id,
        //and if you use the BiomeDictionary, you can even check if the biome has certain tags like swamp or snowy.
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            // All structures needs to be added by .addStructure AND .addFeature in order to spawn.
            //
            // .addStructure tells Minecraft that this biome can start the generation of the structure.
            // .addFeature tells Minecraft that the pieces of the structure can be made in this biome.
            //
            // Thus it is best practice to do .addFeature for all biomes and do .addStructure as well for
            // the biome you want the structure to spawn in. That way, the structure will only spawn in the
            // biomes you want but will not get cut off when generating if part of it goes into a non-valid biome.
            //
            //Note: If your mappings are out of data,
            //	Biome.addStructure will be Biome.func_225566_b_ ,
            //      Feature.withConfiguration will be Feature.func_225566_b_ ,
            //	ConfiguredFeature.withPlacement will be ConfiguredFeature.func_227228_a_ ,
            //	Placement.configure will be Placement.func_227446_a_
            biome.addStructure(HOUSE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, HOUSE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                    .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }
}
