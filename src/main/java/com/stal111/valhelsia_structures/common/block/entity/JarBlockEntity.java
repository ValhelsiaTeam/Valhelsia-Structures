package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Jar Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.JarBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-11-13
 */
public class JarBlockEntity extends BlockEntity implements Clearable {

    private ItemStack plant = ItemStack.EMPTY;

    public JarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JAR.get(), pos, state);
    }

    public boolean hasPlant() {
        return !getPlant().isEmpty();
    }

    public ItemStack getPlant() {
        return plant;
    }

    public void setPlant(ItemStack plant) {
        this.plant = plant;
        this.setChanged();
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Plant", 10)) {
            this.setPlant(ItemStack.of(tag.getCompound("Plant")));
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
        if (this.hasPlant()) {
            tag.put("Plant", this.getPlant().save(new CompoundTag()));
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
    }

    @Override
    public void clearContent() {
        this.setPlant(ItemStack.EMPTY);
    }
}
