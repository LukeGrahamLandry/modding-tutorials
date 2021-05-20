package ca.lukegrahamlandry.firstmod.blocks;

import ca.lukegrahamlandry.firstmod.init.TileEntityInit;
import ca.lukegrahamlandry.firstmod.tiles.MobSlayerTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MobSlayerBlock extends Block {
    public MobSlayerBlock(AbstractBlock.Properties props) {
        super(props);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.MOB_SLAYER.get().create();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide() && hand == Hand.MAIN_HAND){
            // get the tile entity at that position and make sure that it's the right type
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof MobSlayerTile){
                // toggle whether the slayer is active
                ((MobSlayerTile) tile).toggle();

                // play an aggressive sound so I can tell the click was processed
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundCategory.PLAYERS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
        }

        return super.use(state, world, pos, player, hand, hit);
    }

}
