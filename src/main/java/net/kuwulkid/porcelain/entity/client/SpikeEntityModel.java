package net.kuwulkid.porcelain.entity.client;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.unused.SpikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SpikeEntityModel extends GeoModel<SpikeEntity> {
    @Override
    public void setCustomAnimations(SpikeEntity animatable, long instanceId, AnimationState<SpikeEntity> state) {
        super.setCustomAnimations(animatable, instanceId, state);

        EntityModelData extraDataOfType = state.getData(DataTickets.ENTITY_MODEL_DATA);
        software.bernie.geckolib.cache.object.GeoBone head = this.getAnimationProcessor().getBone("head");
        if (head != null) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float)Math.PI / 180F));
        }
    }



    @Override
    public ResourceLocation getModelResource(SpikeEntity object) {
        return ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, "geo/spike.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpikeEntity object) {
        return ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, "textures/entity/spiked.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpikeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID,"animations/strawvillager.animation.json");
    }
    
}
