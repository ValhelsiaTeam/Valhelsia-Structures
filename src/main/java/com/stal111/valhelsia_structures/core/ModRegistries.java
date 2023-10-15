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
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.core.registry.RegistryCollector;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

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
        this.addItemHelper(ModItems::new);
        this.addBlockHelper(ModBlocks::new);
        this.addMappedHelper(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities::new);
        this.addMappedHelper(Registries.RECIPE_SERIALIZER, ModRecipes::new);
        this.addMappedHelper(Registries.STRUCTURE_TYPE, ModStructureTypes::new);
        this.addMappedHelper(Registries.STRUCTURE_POOL_ELEMENT, ModStructurePoolElementTypes::new);
        this.addMappedHelper(Registries.STRUCTURE_PLACEMENT, ModStructurePlacementTypes::new);
        this.addMappedHelper(Registries.STRUCTURE_PROCESSOR, ModStructureProcessors::new);
        this.addMappedHelper(Registries.SOUND_EVENT, ModSoundEvents::new);

        this.addDatapackHelper(Registries.STRUCTURE_SET, ModStructureSets::new);
        this.addDatapackHelper(Registries.STRUCTURE, ModStructures::new);
        this.addDatapackHelper(Registries.TEMPLATE_POOL, (registryResourceKey, s, classCollector) -> new TemplatePoolRegistryHelper(registryResourceKey, s, classCollector, (resourceLocation, holder, projection, terrainAdjustment) -> projection1 -> new ValhelsiaSinglePoolElement(Either.left(resourceLocation), holder, projection, terrainAdjustment)), (info, context) -> ImmutableList.of(new SpawnerDungeonPools(info, (BootstapContext<StructureTemplatePool>) context), new SimpleStructurePools(info, (BootstapContext<StructureTemplatePool>) context), new BigTreePools(info, (BootstapContext<StructureTemplatePool>) context), new DesertHousePools(info, (BootstapContext<StructureTemplatePool>) context), new MobPools(info, (BootstapContext<StructureTemplatePool>) context), new PlayerHousePools(info, (BootstapContext<StructureTemplatePool>) context)));
        this.addDatapackHelper(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::new);
        this.addDatapackHelper(Registries.PROCESSOR_LIST, ModProcessorLists::new);
    }
}
