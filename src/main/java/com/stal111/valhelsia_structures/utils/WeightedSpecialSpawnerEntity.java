package com.stal111.valhelsia_structures.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;

/**
 * WeightedSpecialSpawnerEntity
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.WeightedSpecialSpawnerEntity
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2020-03-22
 */
public class WeightedSpecialSpawnerEntity extends WeightedRandom.Item {
    private final CompoundNBT nbt;

    public WeightedSpecialSpawnerEntity() {
        super(1);
        this.nbt = new CompoundNBT();
        this.nbt.putString("id", "minecraft:zombie");
    }

    public WeightedSpecialSpawnerEntity(CompoundNBT compoundNBT) {
        this(compoundNBT.contains("Weight", 99) ? compoundNBT.getInt("Weight") : 1, compoundNBT.getCompound("Entity"));
    }

    public WeightedSpecialSpawnerEntity(int weight, CompoundNBT compoundNBT) {
        super(weight);
        this.nbt = compoundNBT;
    }

    public CompoundNBT toCompoundTag() {
        CompoundNBT compoundNBT = new CompoundNBT();
        if (!this.nbt.contains("id", 8)) {
            this.nbt.putString("id", "minecraft:zombie");
        } else if (!this.nbt.getString("id").contains(":")) {
            this.nbt.putString("id", (new ResourceLocation(this.nbt.getString("id"))).toString());
        }

        compoundNBT.put("Entity", this.nbt);
        compoundNBT.putInt("Weight", this.itemWeight);
        return compoundNBT;
    }

    public CompoundNBT getNbt() {
        return this.nbt;
    }
}