package net.kuwulkid.porcelain.item;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.blocks.ModBlocks;
import net.kuwulkid.porcelain.item.custom.VorpalGemItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

import java.util.Map;

public class ModItems {
    public static final Map<ResourceLocation, Item> ITEMS = Maps.newLinkedHashMap();


    public static final Item VORPAL_GEM = register("vorpal_gem", new VorpalGemItem(Tiers.GOLD, new Item.Properties().durability(42).attributes(VorpalGemItem.createAttributes(Tiers.GOLD, 9, -2.4F)).rarity(Rarity.RARE)));


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
