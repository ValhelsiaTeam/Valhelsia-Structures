package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team - stal111
 * @since 2023-08-13
 */
public class ModSoundEvents implements RegistryClass {

    public static final MappedRegistryHelper<SoundEvent> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getMappedHelper(ForgeRegistries.Keys.SOUND_EVENTS);

    public static final RegistryObject<SoundEvent> DUNGEON_DOOR_OPEN = HELPER.register("block.dungeon_door.open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block.dungeon_door.open")));
    public static final RegistryObject<SoundEvent> DUNGEON_DOOR_CLOSE = HELPER.register("block.dungeon_door.close", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block.dungeon_door.close")));

}
