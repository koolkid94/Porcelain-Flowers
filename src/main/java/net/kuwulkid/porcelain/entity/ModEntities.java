package net.kuwulkid.porcelain.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static void registerModEntities() {
        PorcelainFlowers.LOGGER.info("Registering Entities for " + PorcelainFlowers.MOD_ID);
    }

    public static final EntityType<JungleLaborerEntity> JUNGLELABORER = Registry.register(Registries.ENTITY_TYPE,
           Identifier.of(PorcelainFlowers.MOD_ID, "junglelaborer"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JungleLaborerEntity::new)
                    .dimensions(EntityDimensions.fixed(1,2)).build());
}
