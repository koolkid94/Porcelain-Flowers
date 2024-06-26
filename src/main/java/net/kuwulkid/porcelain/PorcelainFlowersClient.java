package net.kuwulkid.porcelain;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kuwulkid.porcelain.entity.ModEntities;
import net.kuwulkid.porcelain.entity.client.JungleLaborerRenderer;
import software.bernie.geckolib.GeckoLib;

public class PorcelainFlowersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityRendererRegistry.register(ModEntities.JUNGLELABORER, JungleLaborerRenderer::new);

    }
}
