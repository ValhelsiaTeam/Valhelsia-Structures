package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntityType;

public enum  ModTileEntities {
    SPECIAL_SPAWNER(TileEntityType.Builder.create(SpecialMobSpawnerTileEntity::new, ModBlocks.SPECIAL_SPAWNER.getBlock()).build(null));

    private final TileEntityType<?> tileEntity;

    ModTileEntities(TileEntityType<?> tileEntity) {
        this.tileEntity = tileEntity;
    }

    public String getName() {
        return String.valueOf(this).toLowerCase();
    }

    public TileEntityType<?> getTileEntity() {
        if (tileEntity.getRegistryName() == null) {
            tileEntity.setRegistryName(ValhelsiaStructures.MOD_ID, getName());
        }
        return tileEntity;
    }
}
