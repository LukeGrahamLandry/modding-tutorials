package ca.lukegrahamlandry.firstmod.init;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.blocks.MobSlayerBlock;
import ca.lukegrahamlandry.firstmod.blocks.SadBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstModMain.MOD_ID);

    public static final RegistryObject<Block> SMILE_BLOCK = BLOCKS.register("smile_block",
            () -> new Block(Block.Properties.of(Material.STONE).strength(4f, 1200f).requiresCorrectToolForDrops().lightLevel((state) -> 15)));

    public static final RegistryObject<Block> SAD_BLOCK = BLOCKS.register("sad_block",
            () -> new SadBlock(Block.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> MOB_SLAYER = BLOCKS.register("mob_slayer",
            () -> new MobSlayerBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));


    // automatically creates items for all your blocks
    // you could do it manually instead by registering BlockItems in your ItemInit class
    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            // for each block we registered above...
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                // make an item properties object that puts it in your creative tab
                Item.Properties properties = new Item.Properties().tab(ItemInit.ModCreativeTab.instance);

                // make a block item that places the block
                // note, if you have a special block that needs a custom implementation for the BlockItem, just add an if statement here
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);


                // register the block item with the same name as the block
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }
    }
}


