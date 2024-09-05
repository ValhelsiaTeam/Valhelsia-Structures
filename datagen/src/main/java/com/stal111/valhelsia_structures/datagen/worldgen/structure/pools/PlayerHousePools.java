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
 * @version 1.18.2-0.1.0
 * @since 2021-04-27
 */
public class PlayerHousePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public PlayerHousePools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        BuiltInStructurePools.PLAYER_HOUSES.create(HELPER, context, "player_house", builder -> builder.element("house_1").element("house_2"));
        HELPER.create("player_house/feature_plate", context, "player_house", builder -> builder.element("feature_plate_1").element("feature_plate_2"));
        HELPER.create("player_house/farms", context, "player_house", builder -> builder.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee").element("feature_chicken"));
        HELPER.create("player_house/portals_and_farms", context, "player_house", builder -> builder.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee").element("feature_chicken").element("feature_portal_1").element("feature_portal_2").element("feature_portal_3"));
    }
}