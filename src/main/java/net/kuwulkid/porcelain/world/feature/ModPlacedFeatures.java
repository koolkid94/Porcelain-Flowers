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
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FERN_GROVE = registerPlacedFeature("fern_grove");

    public static final ResourceKey<PlacedFeature> FALLEN_TREE = registerPlacedFeature("fallen_tree");

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(bootstrapContext, FERN_GROVE, holderGetter.getOrThrow(ModConfiguredFeatures.FERN_GROVE), CountPlacement.of(UniformInt.of(2, 3)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        PlacementUtils.register(bootstrapContext, FALLEN_TREE, holderGetter.getOrThrow(ModConfiguredFeatures.FALLEN_TREE), RandomOffsetPlacement.vertical(ConstantInt.of(13)), PlacementUtils.HEIGHTMAP);


    }


    public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, id));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier2) {
        return List.of(modifier, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

}
