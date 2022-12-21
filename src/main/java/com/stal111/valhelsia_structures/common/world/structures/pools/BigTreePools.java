package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * Big Tree Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.pools.BigTreePools
 *
 * @author Valhelsia Team
 * @since 2021-04-27
 */
public class BigTreePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.TEMPLATE_POOL);

    public static final ResourceKey<StructureTemplatePool> START = HELPER.createKey("vegetations/big_trees");

    public BigTreePools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HELPER.createPool(START, context, "vegetations", builder -> builder.element("big_tree"));
        HELPER.createPool(HELPER.createKey("vegetations/big_tree_underside"), context, "vegetations", builder -> builder.element("big_tree_underside"));
    }
}