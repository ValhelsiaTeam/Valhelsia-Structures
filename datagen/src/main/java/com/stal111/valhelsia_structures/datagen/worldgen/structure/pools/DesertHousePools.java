package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools;

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.api.common.registry.helper.TemplatePoolRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

/**
 * Player House Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.PlayerHousePools
 *
 * @author Valhelsia Team
 * @since 2021-04-27
 */
public class DesertHousePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public DesertHousePools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        BuiltInStructurePools.DESERT_HOUSES.create(HELPER, context, "desert_house", builder -> builder.element("desert_house"));
        HELPER.create("desert_house/oasis_plate", context, "desert_house", builder -> builder.element("oasis_plate"));
        HELPER.create("desert_house/well_or_oasis_plate", context, "desert_house", builder -> builder.element("well_or_oasis_plate"));
        HELPER.create("desert_house/oasis", context, "desert_house", builder -> builder.element("feature_oasis_1").element("feature_oasis_2"));
        HELPER.create("desert_house/wells_and_oasis", context, "desert_house", builder -> builder.element("feature_oasis_1").element("feature_oasis_2").element("feature_well_1").element("feature_well_2"));
    }
}