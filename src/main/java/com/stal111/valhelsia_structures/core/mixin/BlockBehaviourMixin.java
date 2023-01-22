package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.utils.ValhelsiaBlockProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Function;

/**
 * @author Valhelsia Team
 * @since 2023-01-22
 */
@Mixin(BlockBehaviour.Properties.class)
public class BlockBehaviourMixin implements ValhelsiaBlockProperties {

    @Shadow Function<BlockState, MaterialColor> materialColor;

    public BlockBehaviour.Properties color(Function<BlockState, MaterialColor> materialColor) {
        this.materialColor = materialColor;

        return (BlockBehaviour.Properties) (Object) this;
    }
}
