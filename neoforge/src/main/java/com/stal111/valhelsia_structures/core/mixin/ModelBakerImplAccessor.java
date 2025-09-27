package com.stal111.valhelsia_structures.core.mixin;

import net.minecraft.client.resources.model.ModelBakery;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ModelBakery.ModelBakerImpl.class)
public interface ModelBakerImplAccessor {
    //TODO: remove?
//    @Invoker("<init>")
//    static ModelBakery.ModelBakerImpl createModelBakerImpl(ModelBakery bakery, ModelBakery.TextureGetter function, ModelResourceLocation resourceLocation) {
//        throw new UnsupportedOperationException();
//    }
}
