package com.stal111.valhelsia_structures.data;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

/**
 * @author Valhelsia Team - stal111
 * @since 2023-08-13
 */
public class ModSoundsProvider extends SoundDefinitionsProvider {

    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param helper The existing file helper provided by the event you are initializing this provider in.
     */
    public ModSoundsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, ValhelsiaStructures.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.add(ModSoundEvents.DUNGEON_DOOR_OPEN, SoundDefinition.definition().with(this.simpleSound("dungeon_door_open_1")).with(this.simpleSound("dungeon_door_open_2")).with(this.simpleSound("dungeon_door_open_3")));
        this.add(ModSoundEvents.DUNGEON_DOOR_CLOSE, SoundDefinition.definition().with(this.simpleSound("dungeon_door_close_1")).with(this.simpleSound("dungeon_door_close_2")).with(this.simpleSound("dungeon_door_close_3")));
    }

    private SoundDefinition.Sound simpleSound(String name) {
        return SoundDefinition.Sound.sound(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), SoundDefinition.SoundType.SOUND);
    }
}
