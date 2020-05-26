package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

/**
 * Structure Pieces
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructurePieces
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class ModStructurePieces {

    // Structure Pieces
    public static final IStructurePieceType CASTLE = CastlePieces.CastlePiece::new;
    public static final IStructurePieceType CASTLE_RUIN = CastleRuinPieces.CastleRuinPiece::new;
    public static final IStructurePieceType DESERT_HOUSE = DesertHousePieces.DesertHousePiece::new;
    public static final IStructurePieceType FORGE = ForgePieces.ForgePiece::new;
    public static final IStructurePieceType PLAYER_HOUSE = PlayerHousePieces.PlayerHousePiece::new;
    public static final IStructurePieceType SMALL_DUNGEON = SmallDungeonPieces.Piece::new;
    public static final IStructurePieceType TOWER_RUIN = TowerRuinPieces.TowerRuinPiece::new;

    public static void registerPieces() {
        // Register Jigsaw Pieces
        CastlePieces.register();
        CastleRuinPieces.register();
        DesertHousePieces.register();
        ForgePieces.register();
        PlayerHousePieces.register();
        SmallDungeonPieces.register();
        TowerRuinPieces.register();

        // Register Structure Pieces
        register(CastleStructure.SHORT_NAME, CASTLE);
        register(CastleRuinStructure.SHORT_NAME, CASTLE_RUIN);
        register(DesertHouseStructure.SHORT_NAME, DESERT_HOUSE);
        register(ForgeStructure.SHORT_NAME, FORGE);
        register(PlayerHouseStructure.SHORT_NAME, PLAYER_HOUSE);
        register(SmallDungeonStructure.SHORT_NAME, SMALL_DUNGEON);
        register(TowerRuinStructure.SHORT_NAME, TOWER_RUIN);

    }

    private static void register(String key, IStructurePieceType type) {
        Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(ValhelsiaStructures.MOD_ID, key), type);
    }
}
