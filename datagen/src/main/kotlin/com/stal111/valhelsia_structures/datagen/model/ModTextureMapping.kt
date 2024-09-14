package com.stal111.valhelsia_structures.datagen.model

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block

object ModTextureMapping {
    private const val POST = "post"
    private const val BRAZIER = "brazier"
    private const val BUNDLED_POSTS = "bundled_posts"
    private const val SLEEPING_BAG = "sleeping_bag"
    private const val JAR = "jar"

    fun post(block: Block): TextureMapping {
        return TextureMapping()
            .put(TextureSlot.SIDE, getBlockTexture(block, POST))
            .put(TextureSlot.END, getBlockTexture(block, POST, "_top"))
            .put(TextureSlot.PARTICLE, getBlockTexture(block, POST))
    }

    fun brazier(block: Block, lit: Boolean): TextureMapping {
        var mapping = TextureMapping().put(TextureSlot.TOP, getBlockTexture(block, BRAZIER, "_top"))

        if (lit) {
            mapping = mapping.put(TextureSlot.FIRE, getBlockTexture(block, BRAZIER, "_fire"))
        }

        return mapping
    }

    fun bundledPosts(block: Block): TextureMapping {
        return TextureMapping()
            .put(TextureSlot.SIDE, getBlockTexture(block, BUNDLED_POSTS))
            .put(TextureSlot.END, getBlockTexture(block, BUNDLED_POSTS, "_top"))
            .put(TextureSlot.PARTICLE, getBlockTexture(block, BUNDLED_POSTS))
    }

    fun metalFramedGlassPane(block: Block): TextureMapping {
        return TextureMapping()
            .put(TextureSlot.PANE, getBlockTexture(block))
            .put(TextureSlot.EDGE, ValhelsiaStructures.location("block/metal_framed_glass_pane_top"))
    }

    fun sleepingBag(block: Block): TextureMapping {
        return TextureMapping().put(TextureSlot.TEXTURE, getBlockTexture(block, SLEEPING_BAG))
    }

    fun jar(block: Block): TextureMapping {
        return TextureMapping.defaultTexture(getBlockTexture(block, JAR))
    }

    private fun getBlockTexture(block: Block): ResourceLocation {
        val resourcelocation = BuiltInRegistries.BLOCK.getKey(block)
        return resourcelocation.withPrefix("block/")
    }

    fun getBlockTexture(block: Block, folder: String): ResourceLocation {
        val resourcelocation = BuiltInRegistries.BLOCK.getKey(block)
        return resourcelocation.withPrefix("block/$folder/")
    }

    fun getBlockTexture(block: Block, folder: String, suffix: String?): ResourceLocation {
        val resourcelocation = BuiltInRegistries.BLOCK.getKey(block)
        return resourcelocation.withPrefix("block/$folder/").withSuffix(suffix)
    }
}
