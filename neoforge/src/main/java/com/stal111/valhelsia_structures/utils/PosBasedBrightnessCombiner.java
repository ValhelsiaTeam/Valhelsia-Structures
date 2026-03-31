package com.stal111.valhelsia_structures.utils;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.level.block.DoubleBlockCombiner;

import javax.annotation.Nonnull;

public class PosBasedBrightnessCombiner implements DoubleBlockCombiner.Combiner<BlockPos, Int2IntFunction> {

    @Nonnull
    public Int2IntFunction acceptDouble(@Nonnull BlockPos first, @Nonnull BlockPos second) {
        return (_) -> {
            ClientLevel level = Minecraft.getInstance().level;

            int i = LevelRenderer.getLightCoords(level, first);
            int j = LevelRenderer.getLightCoords(level, second);
            int k = LightCoordsUtil.block(i);
            int l = LightCoordsUtil.block(j);
            int i1 = LightCoordsUtil.sky(i);
            int j1 = LightCoordsUtil.sky(j);
            return LightCoordsUtil.pack(Math.max(k, l), Math.max(i1, j1));
        };
    }

    @Nonnull
    public Int2IntFunction acceptSingle(@Nonnull BlockPos single) {
        return (value) -> value;
    }

    @Nonnull
    public Int2IntFunction acceptNone() {
        return (value) -> value;
    }
}
