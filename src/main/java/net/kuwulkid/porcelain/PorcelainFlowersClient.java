package net.kuwulkid.porcelain;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.entity.ModEntities;
import net.kuwulkid.porcelain.entity.client.JungleLaborerRenderer;
import net.minecraft.client.renderer.RenderType;

public class PorcelainFlowersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.BEACH_FERN,
                ModBlocks.ARTIFACT_ALTAR
        );

        EntityRendererRegistry.register(ModEntities.JUNGLELABORER, JungleLaborerRenderer::new);

        //EntityRendererRegistry.register(ModEntities.SPIKE, SpikeRenderer::new);

    }
}
