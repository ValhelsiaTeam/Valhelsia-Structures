package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.EntityRegistryHelper;

/**
 * Mod Entities <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModEntities
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2021-02-20
 */
public class ModEntities implements RegistryClass {

    public static final EntityRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ForgeRegistries.Keys.ENTITY_TYPES);

    public static final RegistryEntry<EntityType<MossySkeletonEntity>> MOSSY_SKELETON = HELPER.register("mossy_skeleton", EntityType.Builder.of(MossySkeletonEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8));

}