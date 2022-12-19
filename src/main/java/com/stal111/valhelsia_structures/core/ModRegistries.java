package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import net.minecraft.core.registries.Registries;
import net.valhelsia.valhelsia_core.core.registry.RegistryCollector;
import net.valhelsia.valhelsia_core.core.registry.helper.block.BlockRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-12-19
 */
public class ModRegistries extends RegistryCollector {

    public ModRegistries(String modId) {
        super(modId);
    }

    @Override
    protected void collect() {
        this.add(Registries.BLOCK, BlockRegistryHelper::new, ModBlocks::new);
        this.add(Registries.ITEM, ModItems::new);
        this.add(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities::new);
        this.add(Registries.ENTITY_TYPE, ModEntities::new);
        this.add(Registries.RECIPE_SERIALIZER, ModRecipes::new);
        this.add(Registries.STRUCTURE, ModStructures::new);
    }
}
