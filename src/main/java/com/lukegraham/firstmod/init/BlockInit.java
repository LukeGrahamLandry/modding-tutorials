package com.lukegraham.firstmod.init;

import com.lukegraham.firstmod.FirstMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    public static final RegistryObject<Block> SMILE_BLOCK = BLOCKS.register("smile_block",
            () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1200f).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(10)));


    // automaticlly creates items for all blocks
    // you could do it manually instead by registering BlockItems in your ItemInit class
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        // for each block we registered above...
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            // make an item properties object that puts it in your creative tab
            final Item.Properties properties = new Item.Properties().group(ItemInit.ModItemGroup.instance);

            // make a block item that places the block
            final BlockItem blockItem = new BlockItem(block, properties);

            // register the block item with the same name as the block
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }
}
