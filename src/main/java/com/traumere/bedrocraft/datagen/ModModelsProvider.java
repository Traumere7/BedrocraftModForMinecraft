package com.traumere.bedrocraft.datagen;

import com.traumere.bedrocraft.block.ModBlocks;
import com.traumere.bedrocraft.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {

    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOWING_BEDROCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CORE_OF_METAL_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CORE_OF_NATURE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORE_OF_METAL, Models.GENERATED);
    }
}
