package com.stal111.valhelsia_structures.utils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;

public class SpecialWeightedSpawnerEntity extends WeightedRandom.Item {

    private final CompoundNBT nbt;

    public SpecialWeightedSpawnerEntity() {
        super(1);
        this.nbt = new CompoundNBT();
        this.nbt.putString("id", "minecraft:zombie");
    }

    public SpecialWeightedSpawnerEntity(CompoundNBT p_i46715_1_) {
        this(p_i46715_1_.contains("Weight", 99) ? p_i46715_1_.getInt("Weight") : 1, p_i46715_1_.getCompound("Entity"));
    }

    public SpecialWeightedSpawnerEntity(int p_i46716_1_, CompoundNBT p_i46716_2_) {
        super(p_i46716_1_);
        this.nbt = p_i46716_2_;
    }

    public CompoundNBT toCompoundTag() {
        CompoundNBT lvt_1_1_ = new CompoundNBT();
        if (!this.nbt.contains("id", 8)) {
            this.nbt.putString("id", "minecraft:zombie");
        } else if (!this.nbt.getString("id").contains(":")) {
            this.nbt.putString("id", (new ResourceLocation(this.nbt.getString("id"))).toString());
        }

        lvt_1_1_.put("Entity", this.nbt);
        lvt_1_1_.putInt("Weight", this.itemWeight);
        return lvt_1_1_;
    }

    public CompoundNBT getNbt() {
        return this.nbt;
    }
}
