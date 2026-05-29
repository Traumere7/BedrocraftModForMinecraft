package com.traumere.bedrocraft.block.entity;

import com.traumere.bedrocraft.Bedrocraft;
import com.traumere.bedrocraft.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    /*
     注册方块实体，并与对应方块绑定
     */
    public static final BlockEntityType<CompressorBlockEntity> COMPRESSOR_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier("bedrocraft", "compressor_block_entity"),
                    FabricBlockEntityTypeBuilder.create(CompressorBlockEntity::new, ModBlocks.COMPRESSOR).build()
            );

    //  called at onInitialize()
    public static void registerBlockEntities() {
        Bedrocraft.LOGGER.info("Registering the Block Entity: " + Bedrocraft.MOD_ID);
    }
}
