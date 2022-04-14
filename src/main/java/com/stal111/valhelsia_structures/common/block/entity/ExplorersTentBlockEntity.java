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
 * Explorers Tent Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2020-12-10
 */
public class ExplorersTentBlockEntity extends BlockEntity implements DyeableBlockEntity, Clearable {

    private static final int DEFAULT_COLOR = 10511680;

    private Integer color;

    private ItemStack sleepingBag = ItemStack.EMPTY;

    public ExplorersTentBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TENT.get(), pos, state);
    }

    @Override
    public int getColor() {
        return this.color == null ? DEFAULT_COLOR : this.color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    public void setSleepingBag(ItemStack sleepingBag) {
        this.sleepingBag = sleepingBag;
        this.setChanged();
    }

    public ItemStack getSleepingBag() {
        return this.sleepingBag;
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Color")) {
            this.setColor(tag.getInt("Color"));
        }

        if (tag.contains("SleepingBag", 10)) {
            this.setSleepingBag(ItemStack.of(tag.getCompound("SleepingBag")));
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
        if (this.color != null) {
            tag.putInt("Color", this.getColor());
        }

        if (!this.sleepingBag.isEmpty()) {
            tag.put("SleepingBag", this.sleepingBag.save(new CompoundTag()));
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
        return this.saveWithoutMetadata();
    }

    @Override
    public void clearContent() {
        this.setSleepingBag(ItemStack.EMPTY);
    }
}
