package com.stal111.valhelsia_structures.datagen

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import com.stal111.valhelsia_structures.core.init.ModSoundEvents
import net.neoforged.neoforge.common.data.SoundDefinition
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider
import net.valhelsia.dataforge.DataProviderContext

class ModSoundsProvider(context: DataProviderContext) : SoundDefinitionsProvider(
    context.packOutput,
    ValhelsiaStructures.MOD_ID,
    context.fileHelper
) {
    override fun registerSounds() {
        this.add(
            ModSoundEvents.DUNGEON_DOOR_OPEN, SoundDefinition.definition().with(
                this.simpleSound("dungeon_door_open_1")
            ).with(this.simpleSound("dungeon_door_open_2")).with(this.simpleSound("dungeon_door_open_3"))
        )
        this.add(
            ModSoundEvents.DUNGEON_DOOR_CLOSE, SoundDefinition.definition().with(
                this.simpleSound("dungeon_door_close_1")
            ).with(this.simpleSound("dungeon_door_close_2")).with(this.simpleSound("dungeon_door_close_3"))
        )
    }

    private fun simpleSound(name: String): SoundDefinition.Sound {
        return SoundDefinition.Sound.sound(ValhelsiaStructures.location(name), SoundDefinition.SoundType.SOUND)
    }
}
