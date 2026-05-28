package com.traumere.bedrocraft;

import com.traumere.bedrocraft.block.ModBlocks;
import com.traumere.bedrocraft.item.ModItems;
import com.traumere.bedrocraft.world.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bedrocraft implements ModInitializer {
	public static final String MOD_ID = "bedrocraft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.register();

		LOGGER.info("Hello Fabric world!");

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            // 确保在服务端运行
            if (!world.isClient() && state.isOf(Blocks.BEDROCK)) {
                if (player.getMainHandStack().isOf(ModItems.BEDROCK_PICKAXE)) {
                    // 在被破坏的坐标处生成一个基岩物品实体
                    Block.dropStack(world, pos, new ItemStack(Items.BEDROCK));
                }
            }
        });
	}
}