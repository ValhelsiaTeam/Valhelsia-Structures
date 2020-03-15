package com.stal111.valhelsia_structures.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpecialMobSpawnerTileEntityRenderer extends TileEntityRenderer<SpecialMobSpawnerTileEntity> {

    public SpecialMobSpawnerTileEntityRenderer(TileEntityRendererDispatcher p_i226016_1_) {
        super(p_i226016_1_);
    }

    public void render(SpecialMobSpawnerTileEntity p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
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