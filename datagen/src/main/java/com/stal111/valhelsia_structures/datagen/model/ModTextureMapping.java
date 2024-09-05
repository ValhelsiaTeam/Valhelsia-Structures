package com.stal111.valhelsia_structures.datagen.model;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/**
 * @author Vahelsia Team - stal111
 * @since 07.08.2024
 */
public class ModTextureMapping {

    private static final String POST = "post";
    private static final String BRAZIER = "brazier";
    private static final String BUNDLED_POSTS = "bundled_posts";
    private static final String SLEEPING_BAG = "sleeping_bag";

    public static TextureMapping post(Block block) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, POST))
                .put(TextureSlot.END, getBlockTexture(block, POST, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(block, POST));
    }

    public static TextureMapping brazier(Block block, boolean lit) {
        var mapping = new TextureMapping().put(TextureSlot.TOP, getBlockTexture(block, BRAZIER, "_top"));

        if (lit) {
            mapping = mapping.put(TextureSlot.FIRE, getBlockTexture(block, BRAZIER, "_fire"));
        }

        return mapping;
    }

    public static TextureMapping bundledPosts(Block block) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, BUNDLED_POSTS))
                .put(TextureSlot.END, getBlockTexture(block, BUNDLED_POSTS, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(block, BUNDLED_POSTS));
    }

    public static TextureMapping metalFramedGlassPane(Block block) {
        return new TextureMapping()
                .put(TextureSlot.PANE, getBlockTexture(block))
                .put(TextureSlot.EDGE, ValhelsiaStructures.location("block/metal_framed_glass_pane_top"));
    }

    public static TextureMapping sleepingBag(Block block) {
        return new TextureMapping().put(TextureSlot.TEXTURE, getBlockTexture(block, SLEEPING_BAG));
    }

    public static ResourceLocation getBlockTexture(Block block) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPrefix("block/");
    }

    public static ResourceLocation getBlockTexture(Block block, String folder) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPrefix("block/" + folder + "/");
    }

    public static ResourceLocation getBlockTexture(Block block, String folder, String suffix) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPrefix("block/" + folder + "/").withSuffix(suffix);
    }
}
