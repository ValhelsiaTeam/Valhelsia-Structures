package com.stal111.valhelsia_structures;

import com.stal111.valhelsia_structures.block.SpecialSpawnerBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.proxy.ClientProxy;
import com.stal111.valhelsia_structures.proxy.IProxy;
import com.stal111.valhelsia_structures.proxy.ServerProxy;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntityRenderer;
import com.stal111.valhelsia_structures.world.WorldGen;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.MobSpawnerTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures {
    public static final String MOD_ID = "valhelsia_structures";

    private static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public ValhelsiaStructures() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        WorldGen.setupWorldGen();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            for (ModBlocks block : ModBlocks.values()) {
                blockRegistryEvent.getRegistry().register(block.getBlock());
            }
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            for (ModBlocks block : ModBlocks.values()) {
                BlockItem item = new BlockItem(block.getBlock(), new Item.Properties());
                item.setRegistryName(block.getName());
                itemRegistryEvent.getRegistry().register(item);
            }
        }

        @SubscribeEvent
        public static void onTileEntitiesRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent) {
            for (ModTileEntities tileEntity : ModTileEntities.values()) {
                tileEntityRegistryEvent.getRegistry().register(tileEntity.getTileEntity());
            }
        }
    }
}
