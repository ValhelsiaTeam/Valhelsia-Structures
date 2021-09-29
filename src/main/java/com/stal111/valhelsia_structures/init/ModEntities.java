package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.entity.MossySkeletonEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.registry.EntityRegistryHelper;

/**
 * Mod Entities
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModEntities
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final EntityRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getEntityHelper();

    public static final RegistryObject<EntityType<MossySkeletonEntity>> MOSSY_SKELETON = HELPER.register("mossy_skeleton", EntityType.Builder.create(MossySkeletonEntity::new, EntityClassification.MONSTER).size(0.6F, 1.99F).trackingRange(8));

}