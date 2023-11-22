package com.stal111.valhelsia_structures.core;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.*;
import com.stal111.valhelsia_structures.data.worldgen.modifier.ModBiomeModifiers;
import com.stal111.valhelsia_structures.data.worldgen.processors.ModProcessorLists;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryCollector;
import net.valhelsia.valhelsia_core.api.common.registry.helper.EntityRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.TemplatePoolRegistryHelper;

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
        this.addMappedHelper(ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES, ModStructureHeightProviderTypes.class);

        this.addDatapackHelper(Registries.STRUCTURE_SET, ModStructureSets::new);
        this.addDatapackHelper(Registries.STRUCTURE, ModStructures::new);

        this.addDatapackHelper(Registries.TEMPLATE_POOL, (registryResourceKey, s, datapackClassCollector) -> {
            return new TemplatePoolRegistryHelper(registryResourceKey, s, datapackClassCollector, (resourceLocation, holder, projection, terrainAdjustment) -> {
                return projection1 -> {
                    return new ValhelsiaSinglePoolElement(Either.left(resourceLocation), holder, projection, terrainAdjustment);
                };
            });
        }, context -> ImmutableList.of(new SpawnerDungeonPools((BootstapContext<StructureTemplatePool>) context), new SimpleStructurePools((BootstapContext<StructureTemplatePool>) context), new BigTreePools((BootstapContext<StructureTemplatePool>) context), new DesertHousePools((BootstapContext<StructureTemplatePool>) context), new MobPools((BootstapContext<StructureTemplatePool>) context), new PlayerHousePools((BootstapContext<StructureTemplatePool>) context)));
        this.addDatapackHelper(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::new);
        this.addDatapackHelper(Registries.PROCESSOR_LIST, ModProcessorLists::new);
    }
}
