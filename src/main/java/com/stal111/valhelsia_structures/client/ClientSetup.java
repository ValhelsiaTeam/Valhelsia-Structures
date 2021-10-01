package com.stal111.valhelsia_structures.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Client Setup <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.ClientSetup
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
public class ClientSetup {

    public ClientSetup() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onClientSetup);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
//        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MOSSY_SKELETON.get(), MossySkeletonRenderer::new);
//
//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SPECIAL_SPAWNER.get(), SpecialMobSpawnerTileEntityRenderer::new);
//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.JAR.get(), JarTileEntityRenderer::new);
//        //ClientRegistry.bindTileEntityRenderer(ModTileEntities.TENT.get(), ExplorersTentTileEntityRenderer::new);
//        //ClientRegistry.bindTileEntityRenderer(ModTileEntities.GIANT_FERN.get(), GiantFernTileEntityRenderer::new);
//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.DUNGEON_DOOR.get(), DungeonDoorTileEntityRenderer::new);
    }
}
