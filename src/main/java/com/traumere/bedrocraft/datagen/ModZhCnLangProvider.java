package com.traumere.bedrocraft.datagen;

import com.traumere.bedrocraft.block.ModBlocks;
import com.traumere.bedrocraft.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModZhCnLangProvider extends FabricLanguageProvider {

    public ModZhCnLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CORE_OF_NATURE, "自然核心");
        translationBuilder.add(ModItems.CORE_OF_METAL, "金属核心");

        translationBuilder.add(ModBlocks.GLOWING_BEDROCK, "发光基岩");
        translationBuilder.add(ModBlocks.CORE_OF_METAL_ORE, "金属核心矿石");
    }
}
