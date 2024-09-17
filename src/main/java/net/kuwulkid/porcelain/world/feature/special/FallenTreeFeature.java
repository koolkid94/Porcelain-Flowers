package net.kuwulkid.porcelain.world.feature.special;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Random;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig>
{

    public final Block woodType;
    public final int length;


    public FallenTreeFeature(Codec<FallenTreeFeatureConfig> codec, Block woodType, int length) {
        super(codec);
        this.woodType = woodType;
        this.length = length;
        //so i have the configuration done through the "FallenTreeFeature" constructor, when i think its *supposed to* be handled through the "FallenTreeFeatureConfig" class
        //so this means that the generated "fallen_tree.json" in the configured_feature tab does less than i think, though it *may* handle how frequently it spawns, idk
    }


    @Override
    public boolean place(FeaturePlaceContext<FallenTreeFeatureConfig> context) {
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();

        // find the surface of the world
        BlockPos placement = new BlockPos(origin);
        Block placedBlock = this.woodType;
        int logLength = this.length;

        if (world.getBlockState(placement.above()).is(Blocks.AIR) && world.getBlockState(placement.below()).is(BlockTags.DIRT)) /*makes sure the start of the fallen tree's blocks are generated sandwiched between dirt and air, so the surface*/ {
            if (getRandomBoolean()) {//ns or ew
                dothelogNS(world, placement, placedBlock, logLength);
                return true;
            } else {
                dothelogEW(world, placement, placedBlock, logLength);
                return true;
            }
        }
        return false;

    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
        //source of randomness
    }

    public void dothelogNS(WorldGenLevel world, BlockPos placement, Block placedBlock, int logLength){
        for (int i = 0; i < logLength; )
        {
            if(world.getBlockState(placement.west(logLength)).is(BlockTags.REPLACEABLE) && world.getBlockState(placement.west(logLength).below()).is(BlockTags.DIRT)) //final check to make sure the log is not boring into a hillside, disable this if you want
            {
                if (getRandomBoolean()) { //mossy
                    world.setBlock(placement.west(i), placedBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                    world.setBlock(placement.west(i).above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                } else { //bare
                    world.setBlock(placement.west(i), placedBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                }
            }
            i++;
        }
    }

    public void dothelogEW(WorldGenLevel world, BlockPos placement, Block placedBlock, int logLength){
        for (int i = 0; i < logLength; )
        {
            if(world.getBlockState(placement.north(logLength)).is(BlockTags.REPLACEABLE) && world.getBlockState(placement.north(logLength).below()).is(BlockTags.DIRT)){ //final check to make sure the log is not boring into a hillside, disable this if you want
                //the second qualifier ensures that the log doesnt generate over air, so its not floating
                if (getRandomBoolean()) { //mossy
                    world.setBlock(placement.north(i), placedBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                    world.setBlock(placement.north(i).above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                } else { //bare
                    world.setBlock(placement.north(i), placedBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                }
            }
            i++;
        }
    }


}
