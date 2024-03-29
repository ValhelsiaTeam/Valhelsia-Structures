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
 * @version 1.18.2-0.1.0
 * @since 2021-04-27
 */
public class PlayerHousePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.TEMPLATE_POOL);
    public static final StartPoolKeySet START = StartPoolKeySet.withFurnishedVariant(HELPER, "player_house/houses");

    public PlayerHousePools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        START.create(HELPER, context, "player_house", builder -> builder.element("house_1").element("house_2"));
        HELPER.create("player_house/feature_plate", context, "player_house", builder -> builder.element("feature_plate_1").element("feature_plate_2"));
        HELPER.create("player_house/farms", context, "player_house", builder -> builder.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee").element("feature_chicken"));
        HELPER.create("player_house/portals_and_farms", context, "player_house", builder -> builder.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee").element("feature_chicken").element("feature_portal_1").element("feature_portal_2").element("feature_portal_3"));
    }
}