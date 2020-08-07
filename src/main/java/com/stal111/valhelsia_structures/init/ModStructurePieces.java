package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import com.stal111.valhelsia_structures.world.structures.pieces.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

/**
 * Structure Pieces
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructurePieces
 *
 * @author Valhelsia Team
 * @version 16.0.2
 */
public class ModStructurePieces {

    // Structure Pieces
    public static final IStructurePieceType CASTLE = CastlePieces.CastlePiece::new;
    public static final IStructurePieceType CASTLE_RUIN = CastleRuinPieces.CastleRuinPiece::new;
    public static final IStructurePieceType DESERT_HOUSE = DesertHousePieces.DesertHousePiece::new;
    public static final IStructurePieceType FORGE = ForgePieces.ForgePiece::new;
    public static final IStructurePieceType PLAYER_HOUSE = PlayerHousePieces.PlayerHousePiece::new;
    public static final IStructurePieceType SMALL_DUNGEON = SmallDungeonPieces.SmallDungeonPiece::new;
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
        register(ModStructures.CASTLE.get(), CASTLE);
        register(ModStructures.CASTLE_RUIN.get(), CASTLE_RUIN);
        register(ModStructures.DESERT_HOUSE.get(), DESERT_HOUSE);
        register(ModStructures.FORGE.get(), FORGE);
        register(ModStructures.PLAYER_HOUSE.get(), PLAYER_HOUSE);
        register(ModStructures.SMALL_DUNGEON.get(), SMALL_DUNGEON);
        register(ModStructures.TOWER_RUIN.get(), TOWER_RUIN);

    }

    private static void register(AbstractValhelsiaStructure<?> structure, IStructurePieceType type) {
        Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(ValhelsiaStructures.MOD_ID, structure.getName()), type);
    }
}
