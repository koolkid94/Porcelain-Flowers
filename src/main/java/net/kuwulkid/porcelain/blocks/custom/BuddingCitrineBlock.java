package net.kuwulkid.porcelain.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;


public class BuddingCitrineBlock extends Block
{
    public static final MapCodec<BuddingCitrineBlock> CODEC = simpleCodec(BuddingCitrineBlock::new);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final Direction DIRECTIONS = Direction.DOWN;

    //only has one direction of up and down

    @Override
    public MapCodec<BuddingCitrineBlock> codec() {
        return CODEC;
    }
    
    public BuddingCitrineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
        this.registerDefaultState(defaultBlockState().setValue(POWERED, Boolean.FALSE));
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockPos = hit.getBlockPos();
            level.playSound(null, blockPos, SoundEvents.BASALT_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
            level.playSound(null, blockPos, SoundEvents.BASALT_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
        }
    }


    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
         tick(state, level, pos, random);
        if(level.getBlockState(pos).getValue(POWERED)){
            if (random.nextInt(5) == 0) {
                BlockPos blockPos = pos.relative(DIRECTIONS);
                BlockState blockState = level.getBlockState(blockPos);
                Block block = null;
                if (canClusterGrowAtState(blockState)) {
                    block = Blocks.SMALL_AMETHYST_BUD;
                } else if (blockState.is(Blocks.SMALL_AMETHYST_BUD) && blockState.getValue(AmethystClusterBlock.FACING) == Direction.DOWN) {
                    block = Blocks.MEDIUM_AMETHYST_BUD;
                } else if (blockState.is(Blocks.MEDIUM_AMETHYST_BUD) && blockState.getValue(AmethystClusterBlock.FACING) == Direction.DOWN) {
                    block = Blocks.LARGE_AMETHYST_BUD;
                } else if (blockState.is(Blocks.LARGE_AMETHYST_BUD) && blockState.getValue(AmethystClusterBlock.FACING) == Direction.DOWN) {
                    block = Blocks.AMETHYST_CLUSTER;
                }

                if (block != null) {
                    BlockState blockState2 = block.defaultBlockState()
                            .setValue(AmethystClusterBlock.FACING, Direction.DOWN)
                            .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockState.getFluidState().getType() == Fluids.WATER));
                    level.setBlockAndUpdate(blockPos, blockState2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(lavaAbove(level, pos)){
            if (!level.isClientSide && !level.getBlockState(pos).getValue(POWERED).booleanValue()) {
                level.playSound(null, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                level.playSound(null, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                level.addParticle(ParticleTypes.LANDING_LAVA, (double) pos.getX() + getRand(), (double) pos.getY(), (double) pos.getZ() + getRand(), 0, 0, 0);
                ((ServerLevel) level).sendParticles(ParticleTypes.LANDING_LAVA, pos.getX() + getRand(),pos.getY(), pos.getZ() + getRand(), 1, 0,0, 0 , 0);
            }
            level.setBlock(pos, state.setValue(POWERED, Boolean.TRUE), 3);
        }
            else{
            if (!level.isClientSide && level.getBlockState(pos).getValue(POWERED).booleanValue()) {
                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                level.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + getRand(), (double) pos.getY(), (double) pos.getZ() + getRand(), 0, 0, 0);
                ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE, pos.getX() + getRand(), pos.getY(), pos.getZ() + getRand(), 1, 0,0, 0 , 0);
            }
            level.setBlock(pos, state.setValue(POWERED, Boolean.FALSE), 3);
        }

    }


    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }

    public static boolean lavaAbove(ServerLevel level, BlockPos pos) {
            int newY = pos.getY() + 1;
            if(level.getBlockState(new BlockPos(pos.getX(), newY, pos.getZ())).is(Blocks.LAVA)){
                return true;
            }
            return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    private double getRand(){
        return (double) ((0.9 - 0 + 1) * Math.random() + 0);
    }
}
