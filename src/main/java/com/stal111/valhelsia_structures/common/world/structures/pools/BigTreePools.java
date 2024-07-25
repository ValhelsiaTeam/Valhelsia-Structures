package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.api.common.registry.helper.TemplatePoolRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

/**
 * Big Tree Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.pools.BigTreePools
 *
 * @author Valhelsia Team
 * @since 2021-04-27
 */
public class BigTreePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public static final StartPoolKeySet START = StartPoolKeySet.simple(HELPER, "vegetations/big_trees");

    public BigTreePools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        START.create(HELPER, context, "vegetations", builder -> builder.element("big_tree"));
        HELPER.create("vegetations/big_tree/underside", context, "vegetations", builder -> builder.element("big_tree_underside"));
    }
}