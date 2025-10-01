package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Clearable;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

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
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        input.read("color", ExtraCodecs.RGB_COLOR_CODEC).ifPresent(this::setColor);
        input.read("sleeping_bag", ItemStack.CODEC).ifPresent(this::setSleepingBag);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        if (this.color != DEFAULT_COLOR) {
            output.store("color", ExtraCodecs.RGB_COLOR_CODEC, this.getColor());
        }

        if (!this.sleepingBag.isEmpty()) {
            output.store("sleeping_bag", ItemStack.CODEC, this.getSleepingBag());
        }
    }


    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        super.applyImplicitComponents(componentGetter);

        DyedItemColor color = componentGetter.get(DataComponents.DYED_COLOR);

        if (color != null) {
            this.setColor(color.rgb());
        }
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (!this.sleepingBag.isEmpty()) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), this.getSleepingBag());
        }

        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder components) {
        super.collectImplicitComponents(components);

        components.set(DataComponents.DYED_COLOR, new DyedItemColor(this.getColor()));
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
