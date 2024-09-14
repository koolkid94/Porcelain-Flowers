package net.kuwulkid.porcelain.world.feature;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.world.feature.test.FallenTreeFeatureConfig;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FERN_GROVE = registerConfiguredFeature("fern_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_TREE = registerConfiguredFeature("fallen_tree");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

        FeatureUtils.register(
                bootstrapContext,
                FERN_GROVE,
                ModFeatures.FERN_GROVE,
                new RandomPatchConfiguration(
                        4,
                        6,
                        6,
                        PlacementUtils.filtered(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BEACH_FERN)),
                                BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.SAND))
                        )
                )
        );

        FeatureUtils.register(
                bootstrapContext,
                FALLEN_TREE,
                ModFeatures.FALLEN_TREE,
                new FallenTreeFeatureConfig(
                        1,
                        Blocks.OAK_LOG.defaultBlockState()
                )
        );

    }


    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> bootstapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration) {
        bootstapContext.register(resourceKey, new ConfiguredFeature<>(feature, featureConfiguration));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, id));
    }

}
