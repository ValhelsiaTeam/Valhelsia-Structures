package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Valhelsia Team
 * @since 2020-12-10
 */
public class ExplorersTentBlockEntity extends BlockEntity implements DyeableBlockEntity, Clearable {

    public static final int DEFAULT_COLOR = 10511680;

    private int color = DEFAULT_COLOR;

    private ItemStack sleepingBag = ItemStack.EMPTY;

    public ExplorersTentBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TENT.get(), pos, state);
    }

    @Override
    public int getColor() {
        return this.color;
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

    public ItemStack getAsItem() {
        ItemStack stack = new ItemStack(ModBlocks.EXPLORERS_TENT.get());

        stack.applyComponents(this.collectComponents());

        return stack;
    }

    @Override
    public void loadAdditional(@Nonnull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        super.loadAdditional(tag, lookupProvider);
        if (tag.contains("Color")) {
            this.setColor(tag.getInt("Color"));
        }

        if (tag.contains("SleepingBag", 10)) {
            this.setSleepingBag(ItemStack.parseOptional(lookupProvider, tag.getCompound("SleepingBag")));
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        if (this.color != DEFAULT_COLOR) {
            tag.putInt("Color", this.getColor());
        }

        if (!this.sleepingBag.isEmpty()) {
            tag.put("SleepingBag", this.sleepingBag.save(lookupProvider));
        }
    }

    @Override
    protected void applyImplicitComponents(@NotNull DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);

        DyedItemColor color = componentInput.get(DataComponents.DYED_COLOR);

        if (color != null) {
            this.setColor(color.rgb());
        }
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder components) {
        super.collectImplicitComponents(components);

        components.set(DataComponents.DYED_COLOR, new DyedItemColor(this.getColor(), true));
    }


    @Override
    public void removeComponentsFromTag(@NotNull CompoundTag tag) {
        super.removeComponentsFromTag(tag);
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
        this.setSleepingBag(ItemStack.EMPTY);
    }
}
