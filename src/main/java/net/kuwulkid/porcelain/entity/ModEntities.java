package net.kuwulkid.porcelain.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.entity.custom.JungleLaborerEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModEntities {
    public static void registerModEntities() {
        PorcelainFlowers.LOGGER.info("Registering Entities for " + PorcelainFlowers.MOD_ID);
    }

        public static final EntityType<JungleLaborerEntity> JUNGLELABORER = register(
                "junglelaborer",
                FabricEntityTypeBuilder.createMob()
                        .entityFactory(JungleLaborerEntity::new)
                        .dimensions(EntityDimensions.scalable(1F, 2F))
                        .build()
        );



    @NotNull
    private static <E extends Entity, T extends EntityType<E>> T register(@NotNull String path, @NotNull T entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, PorcelainFlowers.id(path), entityType);
    }



}
