package net.kuwulkid.porcelain.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModEntities {
    public static void registerModEntities() {
        PorcelainFlowers.LOGGER.info("Registering Entities for " + PorcelainFlowers.MOD_ID);
    }

    public static final EntityType<JungleLaborerEntity> JUNGLELABORER = Registry.register(Registries.ENTITY_TYPE,
           Identifier.of(PorcelainFlowers.MOD_ID, "junglelaborer"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, JungleLaborerEntity::new)
                    .dimensions(EntityDimensions.fixed(1,2)).build());

   // public static final Supplier<EntityType<NeutralCreeper>> SAVANNAH_CREEPER = ENTITIES.register("savannah_creeper",
          //  () -> EntityType.Builder.of(BaseCreeper.ofNeutral(CreeperTypes.SAVANNAH), MobCategory.MONSTER).sized(0.6F, 2.2F)
             //       .clientTrackingRange(8).build("savannah_creeper"));
}
