package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * @author Valhelsia Team
 * @since 2023-03-17
 */
public class ModWoodTypes {

    public static final WoodType LAPIDIFIED_JUNGLE = new WoodType(new ResourceLocation(ValhelsiaStructures.MOD_ID, "fungyss").toString(), ModBlocks.LAPIDIFIED_JUNGLE);

    public static void registerWoodTypes() {
        WoodType.register(LAPIDIFIED_JUNGLE);
    }
}
