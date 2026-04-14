package com.traumere.bedrocraft.datagen;

import com.traumere.bedrocraft.block.ModBlocks;
import com.traumere.bedrocraft.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {

    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    public static final List<ItemConvertible> CORE_OF_METAL = List.of(ModBlocks.CORE_OF_METAL_ORE);
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, CORE_OF_METAL, RecipeCategory.MISC, ModItems.CORE_OF_METAL, 0.7f, 200, "core_of_metal");
        offerBlasting(exporter, CORE_OF_METAL, RecipeCategory.MISC, ModItems.CORE_OF_METAL, 0.7f, 100, "core_of_metal");
    }
}
