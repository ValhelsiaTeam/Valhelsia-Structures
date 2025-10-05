package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.client.model.ModModelLayers;
import com.stal111.valhelsia_structures.client.model.block.DungeonDoorModel;
import com.stal111.valhelsia_structures.client.renderer.blockentity.*;
import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Entity Renderer Events <br>
 * Valhelsia Core - com.stal111.valhelsia_structures.client.event.EntityRendererEvents
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
@EventBusSubscriber(value = Dist.CLIENT)
public class EntityRendererEvents {

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.GIANT_FERN, GiantFernRenderer::createLayer);
        event.registerLayerDefinition(DungeonDoorModel.DUNGEON_DOOR, DungeonDoorModel::createLayer);
        event.registerLayerDefinition(ModModelLayers.EXPLORERS_TENT, ExplorersTentRenderer::createLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.JAR.get(), JarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SPECIAL_SPAWNER.get(), SpecialSpawnerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GIANT_FERN.get(), GiantFernRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.DUNGEON_DOOR.get(), DungeonDoorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TENT.get(), ExplorersTentRenderer::new);
    }
}
