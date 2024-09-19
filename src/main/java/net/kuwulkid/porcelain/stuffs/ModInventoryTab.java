package net.kuwulkid.porcelain.stuffs;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.Registry;

public class ModInventoryTab {
    public static final ResourceKey<CreativeModeTab> PORCELAIN_FLOWERS = ResourceKey.create(Registries.CREATIVE_MODE_TAB,  ResourceLocation.fromNamespaceAndPath(PorcelainFlowers.MOD_ID, "porcelain_flowers"));

    public static void initThuglyTab() {



        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PORCELAIN_FLOWERS, FabricItemGroup.builder()

                .icon(ModItems.VORPAL_GEM::getDefaultInstance)
                .title(Component.literal("Porce'lain Flo'wers"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModItems.VORPAL_GEM);
                    output.accept(ModItems.QUARTZ_SWORD);

                    output.accept(ModItems.HUNGERING_SCYTHE);
                    //output.accept(ModItems.SOUL_SCYTHE);

                    output.accept(ModBlocks.ARTIFACT_ALTAR);
                    output.accept(ModBlocks.BEACH_FERN);
                    output.accept(ModBlocks.PERIDOT_ORE);
                    output.accept(ModBlocks.JADE_ORE);

                    output.accept(ModItems.PERIDOT);
                    output.accept(ModItems.JADE);
                    output.accept(ModItems.CITRINE);
                    output.accept(ModItems.HILT);

                    //output.accept(ModItems.JUNGLE_LABORER_SPAWN_EGG);
                    //this is the order the items will appear in within the tab, be mindful

                }).build());



    }





}
