package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.*;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
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
        this.addItemHelper(ModItems.class);
        this.addBlockHelper(ModBlocks.class);
        this.addMappedHelper(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities.class);
        this.addMappedHelper(Registries.RECIPE_SERIALIZER, ModRecipes.class);
        this.addMappedHelper(Registries.STRUCTURE_TYPE, ModStructureTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_POOL_ELEMENT, ModStructurePoolElementTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_PLACEMENT, ModStructurePlacementTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_PROCESSOR, ModStructureProcessors.class);
        this.addMappedHelper(Registries.SOUND_EVENT, ModSoundEvents.class);
        this.addMappedHelper(Registries.CREATIVE_MODE_TAB, ModCreativeModeTabs.class);
        this.addMappedHelper(ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES, ModStructureHeightProviderTypes.class);

        this.addDatapackHelper(Registries.STRUCTURE_SET, ModStructureSets::new);
        this.addDatapackHelper(Registries.STRUCTURE, ModStructures::new);

        this.addDatapackHelper(Registries.TEMPLATE_POOL);
        this.addDatapackHelper(NeoForgeRegistries.Keys.BIOME_MODIFIERS);
        this.addDatapackHelper(Registries.PROCESSOR_LIST);
    }
}
