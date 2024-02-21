package com.stal111.valhelsia_structures.core.mixin;

import com.mojang.logging.LogUtils;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {
    private static boolean hasOptiFine = false;

    static {
        try {
            Class.forName("optifine.Installer");
            hasOptiFine = true;
            LogUtils.getLogger().warn("!!! OptiFine is found !!!");
            LogUtils.getLogger().warn("!!! Things may not work correctly !!!");
        } catch (ClassNotFoundException ignored) {}
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (hasOptiFine) {
            return !mixinClassName.equals("com.stal111.valhelsia_structures.core.mixin.BlockModelRendererMixin");
        } else {
            return !mixinClassName.equals("com.stal111.valhelsia_structures.core.mixin.compat.optifine.BlockModelRendererOFMixin");
        }
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
