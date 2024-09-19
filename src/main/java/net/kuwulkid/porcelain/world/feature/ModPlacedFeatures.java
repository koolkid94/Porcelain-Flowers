package net.kuwulkid.porcelain.world.feature;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;


public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FERN_GROVE = registerPlacedFeature("fern_grove");

    public static final ResourceKey<PlacedFeature> FALLEN_TREE = registerPlacedFeature("fallen_tree");

    public static final ResourceKey<PlacedFeature> PERIDOT_ORE = registerPlacedFeature("peridot_ore");

    public static final ResourceKey<PlacedFeature> JADE_ORE = registerPlacedFeature("jade_ore");


    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(bootstrapContext, FERN_GROVE, holderGetter.getOrThrow(ModConfiguredFeatures.FERN_GROVE), CountPlacement.of(UniformInt.of(2, 3)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        PlacementUtils.register(bootstrapContext, FALLEN_TREE, holderGetter.getOrThrow(ModConfiguredFeatures.FALLEN_TREE), InSquarePlacement.spread(), RandomOffsetPlacement.vertical(ConstantInt.of(-3)), RarityFilter.onAverageOnceEvery(20), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(bootstrapContext, PERIDOT_ORE, holderGetter.getOrThrow(ModConfiguredFeatures.PERIDOT_ORE), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(48))));
        PlacementUtils.register(bootstrapContext, JADE_ORE, holderGetter.getOrThrow(ModConfiguredFeatures.JADE_ORE), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(48))));

    }


    public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, id));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier2) {
        return List.of(modifier, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

}
