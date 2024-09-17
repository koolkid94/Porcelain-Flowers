package net.kuwulkid.porcelain;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kuwulkid.porcelain.tag.BlockTagInfuser;
import net.kuwulkid.porcelain.world.feature.ModConfiguredFeatures;
import net.kuwulkid.porcelain.world.feature.ModConfiguredFeaturesProvider;
import net.kuwulkid.porcelain.world.feature.ModPlacedFeatures;
import net.kuwulkid.porcelain.world.feature.ModPlacedFeaturesProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class PorcelainFlowersDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModConfiguredFeaturesProvider::new);
		pack.addProvider(ModPlacedFeaturesProvider::new);
		pack.addProvider(BlockTagInfuser::new);
		System.out.println("DATAGEN HI");

	}
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder
				.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
				.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
