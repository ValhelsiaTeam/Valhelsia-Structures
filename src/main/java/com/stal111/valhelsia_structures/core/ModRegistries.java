package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.*;
import com.stal111.valhelsia_structures.data.worldgen.processors.ModProcessorLists;
import net.minecraft.core.registries.Registries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryCollector;
import net.valhelsia.valhelsia_core.api.common.registry.helper.EntityRegistryHelper;

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
        this.addMappedHelper(Registries.ENTITY_TYPE, EntityRegistryHelper::new, ModEntities.class);
        this.addMappedHelper(Registries.RECIPE_SERIALIZER, ModRecipes.class);
        this.addMappedHelper(Registries.STRUCTURE_TYPE, ModStructureTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_POOL_ELEMENT, ModStructurePoolElementTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_PLACEMENT, ModStructurePlacementTypes.class);
        this.addMappedHelper(Registries.STRUCTURE_PROCESSOR, ModStructureProcessors.class);
        this.addMappedHelper(Registries.SOUND_EVENT, ModSoundEvents.class);
        this.addMappedHelper(Registries.CREATIVE_MODE_TAB, ModCreativeModeTabs.class);

        this.addDatapackHelper(Registries.STRUCTURE_SET, ModStructureSets::new);
        this.addDatapackHelper(Registries.STRUCTURE, ModStructures::new);

        //TODO
        //this.addDatapackHelper(Registries.TEMPLATE_POOL, (registryResourceKey, s, classCollector) -> new TemplatePoolRegistryHelper(registryResourceKey, s, classCollector, (resourceLocation, holder, projection, terrainAdjustment) -> projection1 -> new ValhelsiaSinglePoolElement(Either.left(resourceLocation), holder, projection, terrainAdjustment)), (info, context) -> ImmutableList.of(new SpawnerDungeonPools(info, (BootstapContext<StructureTemplatePool>) context), new SimpleStructurePools(info, (BootstapContext<StructureTemplatePool>) context), new BigTreePools(info, (BootstapContext<StructureTemplatePool>) context), new DesertHousePools(info, (BootstapContext<StructureTemplatePool>) context), new MobPools(info, (BootstapContext<StructureTemplatePool>) context), new PlayerHousePools(info, (BootstapContext<StructureTemplatePool>) context)));
        //this.addDatapackHelper(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::new);
        this.addDatapackHelper(Registries.PROCESSOR_LIST, ModProcessorLists::new);
    }
}
