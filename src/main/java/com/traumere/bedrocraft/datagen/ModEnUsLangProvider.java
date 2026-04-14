package com.traumere.bedrocraft.datagen;

import com.traumere.bedrocraft.block.ModBlocks;
import com.traumere.bedrocraft.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnUsLangProvider extends FabricLanguageProvider {

    public ModEnUsLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CORE_OF_NATURE, "Core of Nature");
        translationBuilder.add(ModItems.CORE_OF_METAL, "Core of Metal");

        translationBuilder.add(ModBlocks.GLOWING_BEDROCK, "Glowing Bedrock");
        translationBuilder.add(ModBlocks.CORE_OF_METAL_ORE, "Core of Metal Ore");
    }
}
