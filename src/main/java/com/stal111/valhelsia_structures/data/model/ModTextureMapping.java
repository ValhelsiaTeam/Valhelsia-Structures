package com.stal111.valhelsia_structures.data.model;

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

    public static TextureMapping post(Block block) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, POST))
                .put(TextureSlot.END, getBlockTexture(block, POST, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(block, POST));
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
