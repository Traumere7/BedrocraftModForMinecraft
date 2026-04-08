package com.traumere.bedrocraft.item;

import com.traumere.bedrocraft.Bedrocraft;
import com.traumere.bedrocraft.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    /*
    用于注册物品类
     */

    public static final Item CORE_OF_NATURE = registerItems("material/core_of_nature", new Item(new Item.Settings()));
    public static final Item CORE_OF_METAL = registerItems("material/core_of_metal", new Item(new Item.Settings()));

    public static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), new Identifier(Bedrocraft.MOD_ID, id)), item);
    }

    public static Item register(String id, Item item) {
        return register(new Identifier(Bedrocraft.MOD_ID, id), item);
    }

    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    // 将物品加入创造模式物品栏
    public static void addItemToItemGroupIngredients(FabricItemGroupEntries entries) {
        entries.add(CORE_OF_NATURE);
        entries.add(CORE_OF_METAL);
    }

    public static void addItemToItemGroupNatural(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.GLOWING_BEDROCK);
    }

    // 辅助注册方法，用于在主程序中初始化
    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToItemGroupIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemToItemGroupNatural);
    }
}
