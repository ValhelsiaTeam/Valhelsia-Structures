package com.stal111.valhelsia_structures.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UnlitTorchBlock extends BaseTorchBlock {

    public static final MapCodec<UnlitTorchBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("litState").forGetter(UnlitTorchBlock::getLitState),
            propertiesCodec()
    ).apply(instance, UnlitTorchBlock::new));

    private final BlockState litState;

    public UnlitTorchBlock(BlockState litState, Properties properties) {
        super(properties);
        this.litState = litState;
    }

    @Override
    protected @NotNull MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }

    public BlockState getLitState() {
        return this.litState;
    }
}
