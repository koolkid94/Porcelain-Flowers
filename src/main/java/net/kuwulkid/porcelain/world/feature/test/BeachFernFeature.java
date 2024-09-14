package net.kuwulkid.porcelain.world.feature.test;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BeachFernFeature extends Feature<BeachFernFeatureConfig>
{

    public BeachFernFeature(Codec<BeachFernFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BeachFernFeatureConfig> context) {
        WorldGenLevel world = context.level().getLevel();
        // the origin is the place where the game starts trying to place the feature
        BlockPos origin = context.origin();
        // we won't use the random here, but we could if we wanted to
        RandomSource random = context.random();
        BeachFernFeatureConfig config = context.config();

        // don't worry about where these come from-- we'll implement these methods soon
        int number = config.number();
       BlockState blockId = config.blockId();

        BlockState blockState = context.level().getBlockState(origin);
        //not sure if this is correct
        // ensure the ID is okay
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        // find the surface of the world
        BlockPos testPos = new BlockPos(origin);
        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.above();
            // the tag name is dirt, but includes grass, mud, podzol, etc.
            if (world.getBlockState(testPos).is(BlockTags.DIRT)) {
                if (world.getBlockState(testPos.above()).is(Blocks.AIR)) {
                    for (int i = 0; i < number; i++) {
                        // create a simple pillar of blocks
                        world.setBlock(testPos, blockState, 0x10);
                        testPos = testPos.above();

                        // ensure we don't try to place blocks outside the world
                        if (testPos.getY() >= world.getMaxBuildHeight()) break;
                    }
                    return true;
                }
            }
        }
        // the game couldn't find a place to put the pillar
        return false;
    }
}

