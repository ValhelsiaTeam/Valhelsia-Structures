package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools;

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools;
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

/**
 * Big Tree Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.BigTreePools
 *
 * @author Valhelsia Team
 * @since 2021-04-27
 */
public class BigTreePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolHelper HELPER = TemplatePoolHelper.INSTANCE;

    public BigTreePools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        BuiltInStructurePools.BIG_TREES.create(HELPER, context, "vegetations", builder -> builder.element("big_tree"));
        HELPER.create("vegetations/big_tree/underside", context, "vegetations", builder -> builder.element("big_tree_underside"));
    }
}