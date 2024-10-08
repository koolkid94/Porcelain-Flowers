package net.kuwulkid.porcelain.item;

import com.google.common.collect.Maps;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.entity.ModEntities;
import net.kuwulkid.porcelain.item.custom.HungeringScytheItem;
import net.kuwulkid.porcelain.item.custom.QuartzSwordItem;
import net.kuwulkid.porcelain.item.custom.VorpalGemItem;
import net.kuwulkid.porcelain.item.custom.unused.DeleterCubeItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;

import java.util.Map;

import static net.minecraft.world.item.Items.registerItem;

public class ModItems {
    public static final Map<ResourceLocation, Item> ITEMS = Maps.newLinkedHashMap();

    public static final Item VORPAL_GEM = register("vorpal_gem", new VorpalGemItem(ModTiers.AMETHYST, new Item.Properties().durability(42).
            attributes(VorpalGemItem.createAttributes(Tiers.GOLD, 19, -2.4F)).rarity(Rarity.RARE)));

    public static final Item QUARTZ_SWORD = register("quartz_sword", new QuartzSwordItem(ModTiers.QUARTZ, new Item.Properties().durability(42).
            attributes(QuartzSwordItem.createAttributes(Tiers.GOLD, 15, -2.1F)).rarity(Rarity.RARE)));

    public static final Item HUNGERING_SCYTHE = register(
            "hungering_scythe", new HungeringScytheItem(ModTiers.COPPER, new Item.Properties().attributes(HungeringScytheItem.createAttributes(ModTiers.COPPER, 4, -2.4F)).rarity(Rarity.EPIC).durability(238))
    );

    /*public static final Item SOUL_SCYTHE = register(
            "soul_scythe", new HungeringScytheItem(ModTiers.SOUL, new Item.Properties().attributes(HungeringScytheItem.createAttributes(ModTiers.SOUL, 4, -2.4F)).rarity(Rarity.EPIC).durability(238))
    );*/

    public static final Item HILT = register("hilt", new Item(new Item.Properties()));

    public static final Item CITRINE = register("citrine", new Item(new Item.Properties()));

    public static final Item JADE = register("jade", new Item(new Item.Properties()));

    public static final Item PERIDOT = register("peridot", new Item(new Item.Properties()));

    //public static final Item JUNGLE_LABORER_SPAWN_EGG = registerItem("jungle_laborer_spawn_egg", new SpawnEggItem(ModEntities.JUNGLELABORER, 8459049, 2368710, new Item.Properties()));

    //public static final Item VINE_TOME = register("vinetomeitem", new VineTomeItem(new Item.Properties()));

    //used for testing purposes, from @Zeratul4340
    //https://github.com/Zeratul4340/Omicron43Reliquary/tree/main
    //public static final Item DELETER_CUBE = register("deleter_cube", new DeleterCubeItem(new Item.Properties()));

    public static Item registerBaseItem(String name) {
        return register(name, new Item(new Item.Properties()));
    }

    public static <I extends Item> I register(String name, I item) {
        ModItems.ITEMS.put(PorcelainFlowers.id(name), item);
        return item;
    }

    public static void registerModItems() {
       ModItems.ITEMS.forEach((resourceLocation, item) -> Registry.register(BuiltInRegistries.ITEM, resourceLocation, item));
        ModBlocks.BLOCK_ITEMS.forEach((resourceLocation, item) -> Registry.register(BuiltInRegistries.ITEM, resourceLocation, item));
    }
}
