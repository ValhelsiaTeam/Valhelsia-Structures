package com.stal111.valhelsia_structures.datagen.model

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.client.resources.model.sprite.Material
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block

object ModTextureMapping {
    private const val POST = "post"
    private const val BRAZIER = "brazier"
    private const val BUNDLED_POSTS = "bundled_posts"
    private const val SLEEPING_BAG = "sleeping_bag"
    private const val JAR = "jar"

    fun post(block: Block) = TextureMapping()
        .put(TextureSlot.SIDE, getBlockTexture(block, POST))
        .put(TextureSlot.END, getBlockTexture(block, POST, "_top"))
        .put(TextureSlot.PARTICLE, getBlockTexture(block, POST))

    fun brazier(block: Block, lit: Boolean) = TextureMapping().apply {
        put(TextureSlot.TOP, getBlockTexture(block, BRAZIER, "_top"))

        if (lit) {
            put(TextureSlot.FIRE, getBlockTexture(block, BRAZIER, "_fire"))
        }
    }

    fun bundledPosts(block: Block) = TextureMapping()
        .put(TextureSlot.SIDE, getBlockTexture(block, BUNDLED_POSTS))
        .put(TextureSlot.END, getBlockTexture(block, BUNDLED_POSTS, "_top"))
        .put(TextureSlot.PARTICLE, getBlockTexture(block, BUNDLED_POSTS))

    fun metalFramedGlassPane(block: Block) = TextureMapping()
        .put(TextureSlot.PANE, getBlockTexture(block))
        .put(TextureSlot.EDGE, Material(ValhelsiaStructures.identifier("block/metal_framed_glass_pane_top")))

    fun sleepingBag(block: Block) = TextureMapping().put(TextureSlot.TEXTURE, getBlockTexture(block, SLEEPING_BAG))

    fun jar(block: Block) = TextureMapping.defaultTexture(getBlockTexture(block, JAR))

    private fun getBlockTexture(block: Block) =
        Material(BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"))

    fun getBlockTexture(block: Block, folder: String) =
        Material(BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/$folder/"))

    fun getBlockTexture(block: Block, folder: String, suffix: String) =
        Material(BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/$folder/").withSuffix(suffix))
}
