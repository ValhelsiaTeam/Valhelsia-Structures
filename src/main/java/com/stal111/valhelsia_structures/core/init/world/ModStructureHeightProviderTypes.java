package com.stal111.valhelsia_structures.core.init.world;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.common.world.structures.height.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public class ModStructureHeightProviderTypes implements RegistryClass {

    public static final MappedRegistryHelper<StructureHeightProviderType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES);

    public static final RegistryEntry<StructureHeightProviderType<DefaultHeightProvider>> DEFAULT_HEIGHT = register("default_height", DefaultHeightProvider.CODEC);
    public static final RegistryEntry<StructureHeightProviderType<BelowSurfaceHeightProvider>> BELOW_SURFACE_HEIGHT = register("below_surface_height", BelowSurfaceHeightProvider.CODEC);
    public static final RegistryEntry<StructureHeightProviderType<UniformHeightProvider>> UNIFORM_HEIGHT = register("uniform_height", UniformHeightProvider.CODEC);
    public static final RegistryEntry<StructureHeightProviderType<SpawnerRoomHeightProvider>> SPAWNER_ROOM_HEIGHT = register("spawner_room_height", SpawnerRoomHeightProvider.CODEC);
    public static final RegistryEntry<StructureHeightProviderType<DeepSpawnerRoomHeightProvider>> DEEP_SPAWNER_ROOM_HEIGHT = register("deep_spawner_room_height", DeepSpawnerRoomHeightProvider.CODEC);
    public static final RegistryEntry<StructureHeightProviderType<SurfaceHeightProvider>> SURFACE_HEIGHT = register("surface_height", SurfaceHeightProvider.CODEC);

    private static <P extends StructureHeightProvider> RegistryEntry<StructureHeightProviderType<P>> register(String name, Codec<P> codec) {
        return HELPER.register(name, () -> () -> codec);
    }
}
