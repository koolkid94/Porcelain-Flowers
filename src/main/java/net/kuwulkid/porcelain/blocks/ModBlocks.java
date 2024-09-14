package net.kuwulkid.porcelain.blocks;

import com.google.common.collect.Maps;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.blocks.custom.ArtifactAltarBlock;
import net.kuwulkid.porcelain.blocks.custom.BeachFernBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Map;


public class ModBlocks {

    public static final Map<ResourceLocation, Block> BLOCKS = Maps.newLinkedHashMap();
    public static final Map<ResourceLocation, Item> BLOCK_ITEMS = Maps.newLinkedHashMap();

    public static final Block  BEACH_FERN = registerBlock((String) "beach_fern", new BeachFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final Block  ARTIFACT_ALTAR = registerBlock((String) "artifact_altar", new ArtifactAltarBlock(BlockBehaviour.Properties.of().explosionResistance(28).noOcclusion().destroyTime(10).mapColor(MapColor.STONE).replaceable().sound(SoundType.STONE).pushReaction(PushReaction.IGNORE)));


    //public static final Block  BEACH_GRASSES = registerBlock((String) "beach_grasses", new BeachGrassesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static <B extends Block> B registerBlock(String name, B block) {
        ModBlocks.BLOCK_ITEMS.put(PorcelainFlowers.id(name), new BlockItem(block, new Item.Properties()));
        return ModBlocks.registerNoTabBlock(name, block);
    }

    public static <B extends Block> B registerNoTabBlock(String name, B block) {
        ModBlocks.BLOCKS.put(PorcelainFlowers.id(name), block);
        return block;
    }



    public static void registerModBlocks() {
        ModBlocks.BLOCKS.forEach((resourceLocation, block) -> Registry.register(BuiltInRegistries.BLOCK, resourceLocation, block));
        PorcelainFlowers.LOGGER.info("Registering Blocks for " + PorcelainFlowers.MOD_ID);

    }
}
