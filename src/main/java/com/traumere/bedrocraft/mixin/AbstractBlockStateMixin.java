package com.traumere.bedrocraft.mixin;

import com.traumere.bedrocraft.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @Inject(method = "calcBlockBreakingDelta", at = @At("HEAD"), cancellable = true)
    private void allowMiningUnbreakableBlocks(PlayerEntity player, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        BlockState state = (BlockState) (Object) this;
        float hardness = state.getHardness(world, pos);

        if (hardness == -1.0F && player.getMainHandStack().isOf(ModItems.BEDROCK_PICKAXE)) {
            float customHardness = 50.0F;
            int multiplier = player.canHarvest(state) ? 30 : 100;
            float speed = player.getBlockBreakingSpeed(state);
            cir.setReturnValue(speed / customHardness / (float) multiplier);
        }
    }
}