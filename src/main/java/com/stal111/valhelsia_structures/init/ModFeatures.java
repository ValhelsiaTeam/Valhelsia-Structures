package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.SmallCastleFeature;
import com.stal111.valhelsia_structures.world.structures.SmallCastleStructure;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ValhelsiaStructures.MOD_ID)
public class ModFeatures {

    public static final SmallCastleStructure graveyard = create("small_castle_2", new SmallCastleStructure(NoFeatureConfig::deserialize));

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        registry.register(graveyard);
    }

    public static <T extends Feature<?>> T create(String name, T feature) {
        feature.setRegistryName(ValhelsiaStructures.MOD_ID, name);
        return feature;
    }
}
