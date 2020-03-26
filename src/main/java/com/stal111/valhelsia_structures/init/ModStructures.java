package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.Locale;


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
    public static final Structure<NoFeatureConfig> SMALL_CASTLE = create(SmallCastleStructure.SHORT_NAME, new SmallCastleStructure(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> TOWER_RUIN = create(TowerRuinStructure.SHORT_NAME, new TowerRuinStructure(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> PLAYER_HOUSE = create(PlayerHouseStructure.SHORT_NAME, new PlayerHouseStructure(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> FORGE = create(ForgeStructure.SHORT_NAME, new ForgeStructure(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> SMALL_DUNGEON = create(SmallDungeonStructure.SHORT_NAME, new SmallDungeonStructure(NoFeatureConfig::deserialize));

    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Feature<?>> event) {
        // Register Pieces
        ModStructurePieces.registerPieces();

        // Register Structures
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        registry.register(SMALL_CASTLE);
        registry.register(TOWER_RUIN);
        registry.register(PLAYER_HOUSE);
        registry.register(FORGE);
        registry.register(SMALL_DUNGEON);
    }

    private static @Nonnull <T extends Feature<?>> T create(String name, T feature) {
        feature.setRegistryName(ValhelsiaStructures.MOD_ID, name.toLowerCase(Locale.ROOT));
        return feature;
    }
}
