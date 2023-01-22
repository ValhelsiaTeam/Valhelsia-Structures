package com.stal111.valhelsia_structures.core.mixin;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;

@Mixin(ModelBakery.ModelBakerImpl.class)
public interface ModelBakerImplAccessor {
    @Invoker("<init>")
    static ModelBakery.ModelBakerImpl createModelBakerImpl(ModelBakery bakery, BiFunction<ResourceLocation, Material, TextureAtlasSprite> function, ResourceLocation resourceLocation) {
        throw new UnsupportedOperationException();
    }
}
