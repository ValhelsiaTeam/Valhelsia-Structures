package com.stal111.valhelsia_structures.core.init.world;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.common.world.structures.height.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public class ModStructureHeightProviderTypes implements RegistryClass {

    public static final DeferredRegister<StructureHeightProviderType<?>> TYPES = DeferredRegister.create(new ResourceLocation(ValhelsiaStructures.MOD_ID, "structure_height_provider_types"), ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<StructureHeightProviderType<DefaultHeightProvider>> DEFAULT_HEIGHT = register("default_height", DefaultHeightProvider.CODEC);
    public static final RegistryObject<StructureHeightProviderType<BelowSurfaceHeightProvider>> BELOW_SURFACE_HEIGHT = register("below_surface_height", BelowSurfaceHeightProvider.CODEC);
    public static final RegistryObject<StructureHeightProviderType<UniformHeightProvider>> UNIFORM_HEIGHT = register("uniform_height", UniformHeightProvider.CODEC);
    public static final RegistryObject<StructureHeightProviderType<SpawnerRoomHeightProvider>> SPAWNER_ROOM_HEIGHT = register("spawner_room_height", SpawnerRoomHeightProvider.CODEC);
    public static final RegistryObject<StructureHeightProviderType<DeepSpawnerRoomHeightProvider>> DEEP_SPAWNER_ROOM_HEIGHT = register("deep_spawner_room_height", DeepSpawnerRoomHeightProvider.CODEC);
    public static final RegistryObject<StructureHeightProviderType<SurfaceHeightProvider>> SURFACE_HEIGHT = register("surface_height", SurfaceHeightProvider.CODEC);

    private static <P extends StructureHeightProvider> RegistryObject<StructureHeightProviderType<P>> register(String name, Codec<P> codec) {
        return TYPES.register(name, () -> () -> codec);
    }
}
