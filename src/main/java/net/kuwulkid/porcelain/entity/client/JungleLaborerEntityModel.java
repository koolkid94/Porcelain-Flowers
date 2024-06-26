package net.kuwulkid.porcelain.entity.client;

import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class JungleLaborerEntityModel extends GeoModel<JungleLaborerEntity> {

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
