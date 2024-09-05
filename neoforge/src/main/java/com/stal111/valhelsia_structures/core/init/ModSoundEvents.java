package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team - stal111
 * @since 2023-08-13
 */
public class ModSoundEvents implements RegistryClass {

    public static final MappedRegistryHelper<SoundEvent> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.SOUND_EVENT);

    public static final RegistryEntry<SoundEvent, SoundEvent> DUNGEON_DOOR_OPEN = HELPER.register("block.dungeon_door.open", () -> SoundEvent.createVariableRangeEvent(ValhelsiaStructures.location("block.dungeon_door.open")));
    public static final RegistryEntry<SoundEvent, SoundEvent> DUNGEON_DOOR_CLOSE = HELPER.register("block.dungeon_door.close", () -> SoundEvent.createVariableRangeEvent(ValhelsiaStructures.location("block.dungeon_door.close")));

}
