package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team - stal111
 * @since 2023-08-13
 */
public class ModSoundEvents implements RegistryClass {

    public static final MappedRegistryHelper<SoundEvent> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ForgeRegistries.Keys.SOUND_EVENTS);

    public static final RegistryEntry<SoundEvent> DUNGEON_DOOR_OPEN = HELPER.register("block.dungeon_door.open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block.dungeon_door.open")));
    public static final RegistryEntry<SoundEvent> DUNGEON_DOOR_CLOSE = HELPER.register("block.dungeon_door.close", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block.dungeon_door.close")));

}
