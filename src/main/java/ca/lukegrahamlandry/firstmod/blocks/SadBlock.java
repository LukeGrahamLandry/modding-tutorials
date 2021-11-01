package ca.lukegrahamlandry.firstmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class SadBlock extends Block {
    // used to select the model that has it facing the right way
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public SadBlock(Block.Properties properties) {
        // the properties can set lightValue, slipperiness, speedFactor, jumpFactor, etc
        super(properties);

        // sets a default value for every block state property
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // called when you right click while holding the item
    // called twice: Hand.MAIN_HAND & Hand.OFF_HAND
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);  // get the item stack in the player's hand

        // check that we are on the logical server and the held item is gun powder
        if (!world.isClientSide() && held.getItem() == Items.GUNPOWDER){
            // create an explosion
            // entity, x, y, z, explosion radius, cause fires?, mode
            // mode can be NONE (doesnt effect terrain), BREAK (drops block items), or DESTROY
            world.explode(player, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.BlockInteraction.DESTROY);

            // consume a gunpowder
            held.shrink(1);

            // signal to the game that we successfully processed this interaction
            return InteractionResult.CONSUME;
        }

        return super.use(state, world, pos, player, hand, hit);
    }

    // called when the block is removed by an explosion
    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        // create another explosion to cause a chain reaction
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.BlockInteraction.DESTROY);

        super.wasExploded(world, pos, explosion);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.relative(facing)).getBlock();

        if (plant == Blocks.CACTUS){
            return true;
        } else {
            return super.canSustainPlant(state, world, pos, facing, plantable);
        }
    }

    // allows the block to receive random ticks
    // you could call randomTicks() on the properties instead of overriding this method
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    // called when the block gets a random tick (only server side)
    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
        // check if the block above is air
        BlockState above = world.getBlockState(pos.above());
        if (above.isAir()){

            // make it cactus
            world.setBlockAndUpdate(pos.above(), Blocks.CACTUS.defaultBlockState());
        }
    }

    // adds all the block state properties you want to use
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // gets the correct block state for the player to place
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


}
