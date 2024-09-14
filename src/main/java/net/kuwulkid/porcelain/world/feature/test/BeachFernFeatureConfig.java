package net.kuwulkid.porcelain.world.feature.test;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record BeachFernFeatureConfig (int number, BlockState blockId) implements FeatureConfiguration{
    public static final Codec<BeachFernFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            ExtraCodecs.POSITIVE_INT.fieldOf("number").forGetter(BeachFernFeatureConfig::number),
                            BlockState.CODEC.fieldOf("blockID").forGetter(BeachFernFeatureConfig::blockId))
                    .apply(instance, BeachFernFeatureConfig::new));
}
