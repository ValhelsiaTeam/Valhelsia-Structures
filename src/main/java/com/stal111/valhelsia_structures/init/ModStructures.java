package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.SmallCastleStructure;
import com.stal111.valhelsia_structures.world.structures.TowerRuinStructure;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


/**
 * ValhelsiaStructure Structures
 * ValhelsiaStructure - com.stal111.valhelsia_structure.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ValhelsiaStructures.MOD_ID)
public class ModStructures {
    // Structures
    public static final Structure<NoFeatureConfig> SMALL_CASTLE = create("small_castle", new SmallCastleStructure(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> TOWER_RUIN = create("tower_ruin", new TowerRuinStructure(NoFeatureConfig::deserialize));

    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        registry.register(SMALL_CASTLE);
        registry.register(TOWER_RUIN);
    }

    private static <T extends Feature<?>> T create(String name, T feature) {
        feature.setRegistryName(ValhelsiaStructures.MOD_ID, name);
        return feature;
    }
}
