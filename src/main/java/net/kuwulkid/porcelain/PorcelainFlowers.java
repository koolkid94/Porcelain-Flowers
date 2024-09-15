package net.kuwulkid.porcelain;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.entity.ModEntities;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.kuwulkid.porcelain.item.ModItems;
import net.kuwulkid.porcelain.stuffs.ModInventoryTab;
import net.kuwulkid.porcelain.world.feature.ModFeatures;
import net.kuwulkid.porcelain.world.feature.ModPlacedFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PorcelainFlowers implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "porcelainflowers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModFeatures.init();
		//FeatureConfiguration.init();
		ModInventoryTab.initThuglyTab();


		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FERN_GROVE);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FOREST), GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.FALLEN_TREE);

		LOGGER.info("Hello thug...");


		//FabricDefaultAttributeRegistry.register(ModEntities.JUNGLELABORER, JungleLaborerEntity.createJungleLaborerAttributes());
	}



	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

}