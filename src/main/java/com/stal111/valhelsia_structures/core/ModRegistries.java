package com.stal111.valhelsia_structures.core;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.stal111.valhelsia_structures.common.world.structures.pools.SimpleStructurePools;
import com.stal111.valhelsia_structures.common.world.structures.pools.SpawnerDungeonPools;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePoolElementTypes;
import com.stal111.valhelsia_structures.core.init.world.ModStructureSets;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import com.stal111.valhelsia_structures.data.worldgen.modifier.ModBiomeModifiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.core.registry.RegistryCollector;
import net.valhelsia.valhelsia_core.core.registry.helper.EntityRegistryHelper;
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
        this.addMappedHelper(Registries.ENTITY_TYPE, EntityRegistryHelper::new, ModEntities::new);
        this.addMappedHelper(Registries.RECIPE_SERIALIZER, ModRecipes::new);
        this.addMappedHelper(Registries.STRUCTURE_TYPE, ModStructureTypes::new);
        this.addMappedHelper(Registries.STRUCTURE_POOL_ELEMENT, ModStructurePoolElementTypes::new);
        this.addDatapackHelper(Registries.STRUCTURE_SET, (info, context) -> ImmutableList.of(new ModStructureSets(info, (BootstapContext<StructureSet>) context)));
        this.addDatapackHelper(Registries.STRUCTURE, (info, context) -> ImmutableList.of(new ModStructures(info, (BootstapContext<Structure>) context)));
        this.addDatapackHelper(Registries.TEMPLATE_POOL, (registryResourceKey, s, classCollector) -> new TemplatePoolRegistryHelper(registryResourceKey, s, classCollector, (resourceLocation, holder, projection, terrainAdjustment) -> projection1 -> new ValhelsiaSinglePoolElement(Either.left(resourceLocation), holder, projection, terrainAdjustment)), (info, context) -> ImmutableList.of(new SpawnerDungeonPools(info, (BootstapContext<StructureTemplatePool>) context), new SimpleStructurePools(info, (BootstapContext<StructureTemplatePool>) context)));
        this.addDatapackHelper(ForgeRegistries.Keys.BIOME_MODIFIERS, (info, context) -> ImmutableList.of(new ModBiomeModifiers(info, (BootstapContext<BiomeModifier>) context)));
    }

}
