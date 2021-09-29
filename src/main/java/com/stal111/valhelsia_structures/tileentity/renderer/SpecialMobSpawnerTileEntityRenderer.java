package com.stal111.valhelsia_structures.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class SpecialMobSpawnerTileEntityRenderer extends BlockEntityRenderer<SpecialMobSpawnerTileEntity> {

    public SpecialMobSpawnerTileEntityRenderer(BlockEntityRenderDispatcher p_i226016_1_) {
        super(p_i226016_1_);
    }

    public void render(SpecialMobSpawnerTileEntity p_225616_1_, float p_225616_2_, PoseStack p_225616_3_, MultiBufferSource p_225616_4_, int p_225616_5_, int p_225616_6_) {
        p_225616_3_.push();
        p_225616_3_.translate(0.5D, 0.0D, 0.5D);
        SpecialAbstractSpawner abstractspawner = p_225616_1_.getSpawnerBaseLogic();
        Entity entity = abstractspawner.getCachedEntity();
        if (entity != null) {
            float f = 0.53125F;
            float f1 = Math.max(entity.getWidth(), entity.getHeight());
            if ((double)f1 > 1.0D) {
                f /= f1;
            }

            p_225616_3_.translate(0.0D, (double)0.4F, 0.0D);
            p_225616_3_.rotate(Vector3f.YP.rotationDegrees((float)MathHelper.lerp(p_225616_2_, abstractspawner.getPrevMobRotation(), abstractspawner.getMobRotation()) * 10.0F));
            p_225616_3_.translate(0.0D, (double)-0.2F, 0.0D);
            p_225616_3_.rotate(Vector3f.XP.rotationDegrees(-30.0F));
            p_225616_3_.scale(f, f, f);
            Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0.0D, 0.0D, 0.0D, 0.0F, p_225616_2_, p_225616_3_, p_225616_4_, p_225616_5_);
        }

        p_225616_3_.pop();
    }
}