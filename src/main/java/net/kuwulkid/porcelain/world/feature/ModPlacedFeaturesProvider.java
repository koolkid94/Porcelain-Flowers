package net.kuwulkid.porcelain.world.feature;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class ModPlacedFeaturesProvider extends FabricDynamicRegistryProvider {
    public ModPlacedFeaturesProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, ModPlacedFeatures.FERN_GROVE);
        add(registries, entries, ModPlacedFeatures.FALLEN_TREE);

    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<PlacedFeature> resourceKey) {
        final HolderLookup.RegistryLookup<PlacedFeature> configuredFeatureRegistryLookup = registries.lookupOrThrow(Registries.PLACED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/placed_features";
    }
}
