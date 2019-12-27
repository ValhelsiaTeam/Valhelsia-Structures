package com.stal111.valhelsia_structures.tileentity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpecialMobSpawnerTileEntityRenderer extends TileEntityRenderer<SpecialMobSpawnerTileEntity> {

    @Override
    public void render(SpecialMobSpawnerTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
        renderMob(tileEntityIn.getSpawnerBaseLogic(), x, y, z, partialTicks);
        GlStateManager.popMatrix();
    }

    /**
     * Render the mob inside the mob spawner.
     */
    public static void renderMob(SpecialAbstractSpawner mobSpawnerLogic, double posX, double posY, double posZ, float partialTicks) {
        Entity entity = mobSpawnerLogic.getCachedEntity();
        if (entity != null) {
            float f = 0.53125F;
            float f1 = Math.max(entity.getWidth(), entity.getHeight());
            if ((double)f1 > 1.0D) {
                f /= f1;
            }

            GlStateManager.translatef(0.0F, 0.4F, 0.0F);
            GlStateManager.rotatef((float)MathHelper.lerp((double)partialTicks, mobSpawnerLogic.getPrevMobRotation(), mobSpawnerLogic.getMobRotation()) * 10.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.0F, -0.2F, 0.0F);
            GlStateManager.rotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scalef(f, f, f);
            entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
            Minecraft.getInstance().getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
        }
    }
}