package com.stal111.valhelsia_structures.proxy;

import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.tileentity.renderer.JarTileEntityRenderer;
import com.stal111.valhelsia_structures.tileentity.renderer.SpecialMobSpawnerTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Client Proxy
 * Valhelsia Structures - com.stal111.valhelsia_structures.proxy.ClientProxy
 *
 * @author Valhelsia Team
 * @version 16.0.4
 */
@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SPECIAL_SPAWNER.get(), SpecialMobSpawnerTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.JAR.get(), JarTileEntityRenderer::new);
        RenderTypeLookup.setRenderLayer(ModBlocks.SPECIAL_SPAWNER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BRAZIER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOUL_BRAZIER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.METAL_FRAMED_GLASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HANGING_VINES_BODY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HANGING_VINES.get(), RenderType.getCutout());
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
