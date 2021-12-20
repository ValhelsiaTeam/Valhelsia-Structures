package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.EntityRegistryHelper;

/**
 * Mod Entities <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModEntities
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final EntityRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getEntityHelper();

    public static final RegistryObject<EntityType<MossySkeletonEntity>> MOSSY_SKELETON = HELPER.register("mossy_skeleton", EntityType.Builder.of(MossySkeletonEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8));

}