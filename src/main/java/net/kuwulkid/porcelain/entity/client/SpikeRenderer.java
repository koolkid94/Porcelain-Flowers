package net.kuwulkid.porcelain.entity.client;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.unused.SpikeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpikeRenderer extends GeoEntityRenderer<SpikeEntity> {
    private static final ResourceLocation TEXTURE = PorcelainFlowers.id("textures/entity/spiked.png");


    public SpikeRenderer(EntityRendererProvider.Context context) {
        super(context, new SpikeEntityModel());
        this.shadowRadius = 0.35f;

    }

    public ResourceLocation getTexture() {

        return TEXTURE;
    }


}

