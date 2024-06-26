package net.kuwulkid.porcelain.entity.client;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.loading.json.raw.Bone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class JungleLaborerEntityModel extends GeoModel<JungleLaborerEntity> {

    @Override
    public void setCustomAnimations(JungleLaborerEntity animatable, long instanceId, AnimationState<JungleLaborerEntity> state) {
        super.setCustomAnimations(animatable, instanceId, state);

        EntityModelData extraDataOfType = state.getData(DataTickets.ENTITY_MODEL_DATA);
        var head = this.getAnimationProcessor().getBone("head");
        if (head != null) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float)Math.PI / 180F));
        }
    }


    @Override
    public Identifier getModelResource(JungleLaborerEntity object) {
        return Identifier.of(PorcelainFlowers.MOD_ID, "geo/villager.geo.json");
    }

    @Override
    public Identifier getTextureResource(JungleLaborerEntity object) {
        return Identifier.of(PorcelainFlowers.MOD_ID, "textures/entity/junglestraw.png");
    }

    @Override
    public Identifier getAnimationResource(JungleLaborerEntity animatable) {
        return Identifier.of(PorcelainFlowers.MOD_ID, "animations/strawvillager.animation.json");
    }
}
