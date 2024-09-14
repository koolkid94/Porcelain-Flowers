package net.kuwulkid.porcelain.world.feature;

import com.google.common.collect.Maps;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.Map;

public class ModFeatures {

    public static final Map<ResourceLocation, Feature<?>> FEATURES = Maps.newLinkedHashMap();

    public static final Feature<RandomPatchConfiguration> FERN_GROVE = registerFeature("fern_grove", new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> F registerFeature(String name, F feature) {
        FEATURES.put(PorcelainFlowers.id(name), feature);
        return feature;
    }

    public static void init() {
        for (ResourceLocation id : FEATURES.keySet()) {
            Registry.register(BuiltInRegistries.FEATURE, id, FEATURES.get(id));
        }
    }
}