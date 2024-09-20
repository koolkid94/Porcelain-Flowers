package net.kuwulkid.porcelain.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagInfuser extends FabricTagProvider.BlockTagProvider{

    public BlockTagInfuser(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ARTIFACT_ALTAR, ModBlocks.PERIDOT_ORE, ModBlocks.JADE_ORE, ModBlocks.CITRINE_ORE);
        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.PERIDOT_ORE, ModBlocks.JADE_ORE, ModBlocks.CITRINE_ORE);
    }
}
