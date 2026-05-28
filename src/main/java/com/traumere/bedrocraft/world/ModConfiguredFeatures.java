package com.traumere.bedrocraft.world;

import com.traumere.bedrocraft.Bedrocraft;
import com.traumere.bedrocraft.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> CORE_OF_METAL_ORE_KEY = of("core_of_metal_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest stoneReplace = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplace = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overWorldTarget = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.CORE_OF_METAL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DEEPSLATE_CORE_OF_METAL_ORE.getDefaultState())
        );

        ConfiguredFeatures.register(featureRegisterable, CORE_OF_METAL_ORE_KEY, Feature.ORE,
                new OreFeatureConfig(overWorldTarget, 6));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Bedrocraft.MOD_ID, id));
    }
}
