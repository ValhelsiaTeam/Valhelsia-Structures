package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.*;
import net.minecraft.core.registries.Registries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryCollector;

/**
 * @author Valhelsia Team
 * @since 2022-12-19
 */
public class ModRegistries extends RegistryCollector {

    public ModRegistries(String modId) {
        super(modId);
    }

    @Override
    protected void collectHelpers() {
        this.addBlockHelper(ModBlocks.class);
        this.addItemHelper(ModItems.class);
        this.addHelper(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities.class);
        this.addHelper(Registries.RECIPE_SERIALIZER, ModRecipes.class);
        this.addHelper(Registries.STRUCTURE_TYPE, ModStructureTypes.class);
        this.addHelper(Registries.STRUCTURE_POOL_ELEMENT, ModStructurePoolElementTypes.class);
        this.addHelper(Registries.STRUCTURE_PLACEMENT, ModStructurePlacementTypes.class);
        this.addHelper(Registries.STRUCTURE_PROCESSOR, ModStructureProcessors.class);
        this.addHelper(Registries.SOUND_EVENT, ModSoundEvents.class);
        this.addHelper(Registries.CREATIVE_MODE_TAB, ModCreativeModeTabs.class);
        this.addHelper(ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES, ModStructureHeightProviderTypes.class);
    }
}
