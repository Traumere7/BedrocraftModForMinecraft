package com.traumere.bedrocraft;

import com.traumere.bedrocraft.datagen.*;
import com.traumere.bedrocraft.world.ModConfiguredFeatures;
import com.traumere.bedrocraft.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BedrocraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider(ModEnUsLangProvider::new);
        pack.addProvider(ModItemTagsProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelsProvider::new);
        pack.addProvider(ModRecipesProvider::new);
        pack.addProvider(ModZhCnLangProvider::new);
        pack.addProvider(ModWorldGenerator::new);

    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
