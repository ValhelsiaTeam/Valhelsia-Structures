package com.stal111.valhelsia_structures.setup;

import com.stal111.valhelsia_structures.entity.render.MossySkeletonRenderer;
import com.stal111.valhelsia_structures.init.ModEntities;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.tileentity.renderer.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Client Setup
 * Valhelsia Structures - com.stal111.valhelsia_structures.setup.ClientSetup
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
public class ClientSetup {

    public ClientSetup() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onClientSetup);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MOSSY_SKELETON.get(), MossySkeletonRenderer::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SPECIAL_SPAWNER.get(), SpecialMobSpawnerTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.JAR.get(), JarTileEntityRenderer::new);
        //ClientRegistry.bindTileEntityRenderer(ModTileEntities.TENT.get(), ExplorersTentTileEntityRenderer::new);
        //ClientRegistry.bindTileEntityRenderer(ModTileEntities.GIANT_FERN.get(), GiantFernTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.DUNGEON_DOOR.get(), DungeonDoorTileEntityRenderer::new);
    }
}
