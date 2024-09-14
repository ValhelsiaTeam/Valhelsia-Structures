package com.stal111.valhelsia_structures.datagen.model

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureSlot
import java.util.*

object ModModelTemplates {
    val TEMPLATE_POST: ModelTemplate = create("template_post", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_POST_ATTACHED: ModelTemplate = create("template_post_attached", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_1: ModelTemplate = create("template_cut_post_1", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_1_ATTACHED: ModelTemplate = create("template_cut_post_1_attached", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_2: ModelTemplate = create("template_cut_post_2", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_2_ATTACHED: ModelTemplate = create("template_cut_post_2_attached", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_3: ModelTemplate = create("template_cut_post_3", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_CUT_POST_3_ATTACHED: ModelTemplate = create("template_cut_post_3_attached", TextureSlot.SIDE, TextureSlot.END)
    val TEMPLATE_BRAZIER: ModelTemplate = create("template_brazier", TextureSlot.TOP, TextureSlot.FIRE)
    val TEMPLATE_BRAZIER_OFF: ModelTemplate = create("template_brazier_off", TextureSlot.TOP)
    val SLEEPING_BAG_FOOT: ModelTemplate = create("sleeping_bag_foot", TextureSlot.TEXTURE)
    val SLEEPING_BAG_HEAD: ModelTemplate = create("sleeping_bag_head", TextureSlot.TEXTURE)
    val SLEEPING_BAG_INVENTORY: ModelTemplate = create("sleeping_bag_inventory", TextureSlot.TEXTURE)
    val TEMPLATE_JAR: ModelTemplate = create("template_jar", TextureSlot.TEXTURE)
    val TEMPLATE_JAR_ROTATED: ModelTemplate = create("template_jar_rotated", TextureSlot.TEXTURE)

    private fun create(name: String, vararg textureSlots: TextureSlot): ModelTemplate {
        return ModelTemplate(
            Optional.of(
                ValhelsiaStructures.location(
                    "block/$name"
                )
            ), Optional.empty(), *textureSlots
        )
    }
}
