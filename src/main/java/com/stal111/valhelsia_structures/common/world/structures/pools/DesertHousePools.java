package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * Player House Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.pools.PlayerHousePools
 *
 * @author Valhelsia Team
 * @since 2021-04-27
 */
public class DesertHousePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.TEMPLATE_POOL);

    public static final StartPoolKeySet START = StartPoolKeySet.withFurnishedVariant(HELPER, "desert_house/houses");

    public DesertHousePools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        START.create(HELPER, context, "desert_house", builder -> builder.element("desert_house"));
        HELPER.create("desert_house/oasis_plate", context, "desert_house", builder -> builder.element("oasis_plate"));
        HELPER.create("desert_house/well_or_oasis_plate", context, "desert_house", builder -> builder.element("well_or_oasis_plate"));
        HELPER.create("desert_house/oasis", context, "desert_house", builder -> builder.element("feature_oasis_1").element("feature_oasis_2"));
        HELPER.create("desert_house/wells_and_oasis", context, "desert_house", builder -> builder.element("feature_oasis_1").element("feature_oasis_2").element("feature_well_1").element("feature_well_2"));
    }
}