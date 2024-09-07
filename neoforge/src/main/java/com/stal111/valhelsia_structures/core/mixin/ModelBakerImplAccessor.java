package com.stal111.valhelsia_structures.core.mixin;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelBakery.ModelBakerImpl.class)
public interface ModelBakerImplAccessor {
    @Invoker("<init>")
    static ModelBakery.ModelBakerImpl createModelBakerImpl(ModelBakery bakery, ModelBakery.TextureGetter function, ModelResourceLocation resourceLocation) {
        throw new UnsupportedOperationException();
    }
}
