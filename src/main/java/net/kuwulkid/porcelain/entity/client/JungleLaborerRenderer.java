package net.kuwulkid.porcelain.entity.client;


import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JungleLaborerRenderer extends GeoEntityRenderer<JungleLaborerEntity> {
        private static final Identifier TEXTURE = Identifier.of(PorcelainFlowers.MOD_ID,"textures/entity/junglestraw.png");

        public JungleLaborerRenderer(EntityRendererFactory.Context context) {
            super(context, new JungleLaborerEntityModel());
            this.shadowRadius = 0.35f;

        }

        @Override
        public Identifier getTexture(JungleLaborerEntity entity) {
            return TEXTURE;
        }


}
