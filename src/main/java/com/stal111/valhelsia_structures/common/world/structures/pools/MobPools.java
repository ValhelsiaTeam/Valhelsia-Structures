package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2021-04-26
 */
public class MobPools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.TEMPLATE_POOL);

    public MobPools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        // Entities
        HELPER.createPool(HELPER.createKey("mobs/bee"), context, "mobs", builder -> builder.element("bee"));
        HELPER.createPool(HELPER.createKey("mobs/skeleton"), context, "mobs", builder -> builder.element("skeleton"));
        HELPER.createPool(HELPER.createKey("mobs/pillager_with_axe_1"), context, "mobs", builder -> builder.element("pillager_with_axe_1"));
        HELPER.createPool(HELPER.createKey("mobs/pillager_with_crossbow_1"), context, "mobs", builder -> builder.element("pillager_with_crossbow_1"));
        HELPER.createPool(HELPER.createKey("mobs/pillager_with_sword_1"), context, "mobs", builder -> builder.element("pillager_with_sword_1"));
        HELPER.createPool(HELPER.createKey("mobs/witch_with_cat"), context, "mobs", builder -> builder.element("witch_with_cat"));
        HELPER.createPool(HELPER.createKey("mobs/horse"), context, "mobs", builder -> builder.element("horse"));
        HELPER.createPool(HELPER.createKey("mobs/villagers"), context, "mobs/villagers", builder -> builder.element("villager").element("villager_khytwel").element("villager_vaelzan").element("villager_stal").element("villager_cynthal").element("villager_kanadet"));
        HELPER.createPool(HELPER.createKey("mobs/chickens"), context, "mobs/chickens", builder -> builder.element("chicken_1").element("chicken_2").element("chicken_3"));

        // Spawners
        HELPER.createPool(HELPER.createKey("mobs/spawners/zombie_or_skeleton_or_spider"), context, "mobs/spawners", builder -> builder.element("zombie").element("skeleton").element("spider"));
        HELPER.createPool(HELPER.createKey("mobs/spawners/zombie"), context, "mobs/spawners", builder -> builder.element("zombie"));
        HELPER.createPool(HELPER.createKey("mobs/spawners/skeleton"), context, "mobs/spawners", builder -> builder.element("skeleton"));
        HELPER.createPool(HELPER.createKey("mobs/spawners/spider"), context, "mobs/spawners", builder -> builder.element("spider"));

        // Special Spawners
        HELPER.createPool(HELPER.createKey("mobs/special_spawners/zombie_or_skeleton_or_spider"), context, "mobs/special_spawners", builder -> builder.element("zombie").element("skeleton").element("spider"));
        HELPER.createPool(HELPER.createKey("mobs/special_spawners/drowned"), context, "mobs/special_spawners", builder -> builder.element("drowned"));
    }
}
