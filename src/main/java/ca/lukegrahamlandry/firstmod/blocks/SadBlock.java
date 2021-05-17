package ca.lukegrahamlandry.firstmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class SadBlock extends Block {
    // used to select the model that has it facing the right way
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public SadBlock(Block.Properties properties) {
        // the properties can set lightValue, slipperiness, speedFactor, jumpFactor, etc
        super(properties);

        // sets a default value for every block state property
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // called when you right click while holding the item
    // called twice: Hand.MAIN_HAND & Hand.OFF_HAND
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack held = player.getItemInHand(hand);  // get the item stack in the player's hand

        // check that we are on the logical server and the held item is gun powder
        if (!world.isClientSide() && held.getItem() == Items.GUNPOWDER){
            // create an explosion
            // entity, x, y, z, explosion radius, cause fires?, mode
            // mode can be NONE (doesnt effect terrain), BREAK (drops block items), or DESTROY
            world.explode(player, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.Mode.DESTROY);

            // consume a gunpowder
            held.shrink(1);

            // signal to the game that we successfully processed this interaction
            return ActionResultType.CONSUME;
        }

        return super.use(state, world, pos, player, hand, hit);
    }

    // called when the block is removed by an explosion
    @Override
    public void wasExploded(World world, BlockPos pos, Explosion explosion) {
        // create another explosion to cause a chain reaction
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.Mode.DESTROY);

        super.wasExploded(world, pos, explosion);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
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
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        // check if the block above is air
        BlockState above = world.getBlockState(pos.above());
        if (above.isAir()){

            // make it cactus
            world.setBlockAndUpdate(pos.above(), Blocks.CACTUS.defaultBlockState());
        }
    }

    // adds all the block state properties you want to use
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // gets the correct block state for the player to place
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


}
