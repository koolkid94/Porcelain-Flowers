package net.kuwulkid.porcelain.world.feature.test;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.awt.image.TileObserver;
import java.util.Random;

import static net.minecraft.world.level.block.DirectionalBlock.FACING;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig>
{
    int rand = (int) ((15 - 1 + 1) * Math.random() + 1);
//yes i realise how hardcoded this is

    public FallenTreeFeature(Codec<FallenTreeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenTreeFeatureConfig> context) {
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();
        //world.setBlock(pos, Blocks.OAK_LOG.defaultBlockState(), 0x10);


        // find the surface of the world
        BlockPos testPos = new BlockPos(origin);
        BlockPos thug = new BlockPos(origin);
        int x = thug.getX() + rand;
        int y = thug.getY() + rand;
        int z = thug.getZ() + rand;

        //testPos = testPos.offset(x, y ,z);


        //increments testPos to be one up
        // the tag name is dirt, but includes grass, mud, podzol, etc.
        //reduces spawn chances
        if(getRandomBoolean()){
            if(getRandomBoolean()){
                if(getRandomBoolean()){
                    if (world.getBlockState(testPos.above()).is(Blocks.AIR) && world.getBlockState(testPos.below()).is(BlockTags.DIRT)) {
                        if(getRandomBoolean())// determines facing eastwest/northsouth
                        {
                            if(getRandomBoolean()) //determines if decorated or not
                            {
                                if(getRandomBoolean()){//determines if decorated w/ moss or mushroom
                                    world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    world.setBlock(testPos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos, Blocks.OAK_LOG);

                                    world.setBlock(testPos.west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    world.setBlock(testPos.west().above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos.west(), Blocks.OAK_LOG);

                                    world.setBlock(testPos.west().west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    world.setBlock(testPos.west(2).above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos.west().west(), Blocks.OAK_LOG);
                                    return true;
                                }

                                else{ //lichen
                                    world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos, Blocks.OAK_LOG);

                                    world.setBlock(testPos.west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.west().above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos.west(), Blocks.OAK_LOG);

                                    world.setBlock(testPos.west().west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.west().west().above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos.west().west(), Blocks.OAK_LOG);
                                    return true;
                                }

                            }
                            else{
                                world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                world.blockUpdated(testPos, Blocks.OAK_LOG);

                                world.setBlock(testPos.west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                world.blockUpdated(testPos.west(), Blocks.OAK_LOG);

                                world.setBlock(testPos.west().west(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.X)), 0x10);
                                world.blockUpdated(testPos.west().west(), Blocks.OAK_LOG);
                                return true;
                            }
                        }
                        else //northsouth
                        {
                            if(getRandomBoolean()) //determines if decorated or not
                            {
                                if(getRandomBoolean()){//determines if decorated w/ moss or mushroom
                                    world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    world.setBlock(testPos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos, Blocks.OAK_LOG);

                                    world.setBlock(testPos.north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    world.setBlock(testPos.north().above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos.north(), Blocks.OAK_LOG);

                                    world.setBlock(testPos.north().north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    world.setBlock(testPos.north(2).above(), Blocks.MOSS_CARPET.defaultBlockState(), 0x10);
                                    world.blockUpdated(testPos.north().north(), Blocks.OAK_LOG);
                                    return true;
                                }

                                else{ //mushroom
                                    world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos, Blocks.OAK_LOG);

                                    world.setBlock(testPos.north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.north().above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos.north(), Blocks.OAK_LOG);

                                    world.setBlock(testPos.north().north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                    if(getRandomBoolean()){
                                        world.setBlock(testPos.north().north().above(), Blocks.RED_MUSHROOM.defaultBlockState(), 0x10);
                                    }
                                    world.blockUpdated(testPos.north().north(), Blocks.OAK_LOG);
                                    return true;
                                }

                            }
                            else{
                                world.setBlock(testPos, Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                world.blockUpdated(testPos, Blocks.OAK_LOG);

                                world.setBlock(testPos.north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                world.blockUpdated(testPos.north(), Blocks.OAK_LOG);

                                world.setBlock(testPos.north().north(), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis.Z)), 0x10);
                                world.blockUpdated(testPos.north().north(), Blocks.OAK_LOG);
                                return true;
                            }
                        }


                    }
                }
            }
        }
        //fallback
        //System.out.println("FAILED TO PLACE");
        return false;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }


}
