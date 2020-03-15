package com.stal111.valhelsia_structures.proxy;

import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ClientRegistry.bindTileEntityRenderer((TileEntityType<SpecialMobSpawnerTileEntity>) ModTileEntities.SPECIAL_SPAWNER.getTileEntity(), SpecialMobSpawnerTileEntityRenderer::new);
        RenderTypeLookup.setRenderLayer(ModBlocks.SPECIAL_SPAWNER.getBlock(), RenderType.cutout());
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
