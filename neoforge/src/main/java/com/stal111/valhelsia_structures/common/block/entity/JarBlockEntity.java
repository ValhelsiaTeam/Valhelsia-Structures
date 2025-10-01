package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Jar Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.JarBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.18.1-0.1.1
 * @since 2020-11-13
 */
public class JarBlockEntity extends BlockEntity implements Clearable {

    private ItemStack plant = ItemStack.EMPTY;

    public JarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JAR.get(), pos, state);
    }

    public boolean hasPlant() {
        return !this.getPlant().isEmpty();
    }

    public ItemStack getPlant() {
        return this.plant;
    }

    public void setPlant(ItemStack plant) {
        this.plant = plant;
        this.setChanged();
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (!this.plant.isEmpty()) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), this.plant);
        }

        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        input.read("plant", ItemStack.CODEC).ifPresent(this::setPlant);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        if (this.hasPlant()) {
            output.store("plant", ItemStack.CODEC, this.getPlant());
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider lookupProvider) {
        return super.getUpdateTag(lookupProvider);
    }

    @Override
    public void clearContent() {
        this.setPlant(ItemStack.EMPTY);
    }
}
