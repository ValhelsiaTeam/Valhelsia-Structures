package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * Valhelsia Structures Structure Pieces
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructurePieces
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2019-10-31
 */

public class ModStructurePieces {
    // Structure Pieces
    public static final IStructurePieceType SMALL_CASTLE = SmallCastlePieces.SmallCastlePiece::new;
    public static final IStructurePieceType TOWER_RUIN = TowerRuinPieces.TowerRuinPiece::new;
    public static final IStructurePieceType PLAYER_HOUSE = PlayerHousePieces.PlayerHousePiece::new;
    public static final IStructurePieceType FORGE = ForgePieces.ForgePiece::new;
    public static final IStructurePieceType SMALL_DUNGEON = SmallDungeonPieces.SmallDungeonPiece::new;

    public static void registerPieces() {
        // Register Jigsaw Pieces
        ForgePieces.register();
        PlayerHousePieces.register();
        SmallCastlePieces.register();
        SmallDungeonPieces.register();
        TowerRuinPieces.register();

        // Register Structure Pieces
        register(ForgeStructure.SHORT_NAME, FORGE);
        register(PlayerHouseStructure.SHORT_NAME, PLAYER_HOUSE);
        register(SmallCastleStructure.SHORT_NAME, SMALL_CASTLE);
        register(SmallDungeonStructure.SHORT_NAME, SMALL_DUNGEON);
        register(TowerRuinStructure.SHORT_NAME, TOWER_RUIN);
    }

    @SuppressWarnings("UnusedReturnValue")
    private static @Nonnull IStructurePieceType register(String name, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(ValhelsiaStructures.MOD_ID, name.toLowerCase(Locale.ROOT)), type);
    }
}
