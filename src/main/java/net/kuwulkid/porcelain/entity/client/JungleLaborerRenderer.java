package net.kuwulkid.porcelain.entity.client;


import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JungleLaborerRenderer extends GeoEntityRenderer<JungleLaborerEntity> {
    private static final ResourceLocation TEXTURE = PorcelainFlowers.id("textures/entity/junglestraw.png");


    public JungleLaborerRenderer(EntityRendererProvider.Context context) {
            super(context, new JungleLaborerEntityModel());
            this.shadowRadius = 0.35f;

        }

    public ResourceLocation getTexture() {

        return TEXTURE;
    }


}
