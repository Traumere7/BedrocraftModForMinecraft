package com.traumere.bedrocraft.block;

import com.traumere.bedrocraft.Bedrocraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    /*
    用于注册方块
    */
    public static final Block GLOWING_BEDROCK = register("glowing_bedrock", new Block(
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .instrument(Instrument.BASEDRUM)
                    .strength(-1.0F, 3600000.0F)
                    .dropsNothing()
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 8)
        )
    );

    public static Block register(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(Bedrocraft.MOD_ID, id), block);
    }

    // 注册方块物品
    public static void registerBlockItems(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Bedrocraft.MOD_ID, id),
                new BlockItem(block, new Item.Settings()));
    }

    // 主程序初始化
    public static void registerModBlocks() {

    }
}
