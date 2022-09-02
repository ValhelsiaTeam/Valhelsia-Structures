package com.stal111.valhelsia_structures.core.data.server.loot;

import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.valhelsia.valhelsia_core.common.loot.conditions.MatchBlockCondition;
import net.valhelsia.valhelsia_core.common.loot.modifiers.AppendLootTableModifier;

/**
 * Mod Loot Modifiers <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.loot.ModLootModifierProvider
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-05-07
 */
public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(DataGenerator gen) {
        super(gen, ValhelsiaStructures.MOD_ID);
    }

    @Override
    protected void start() {
        add("jar_treasure",
                new AppendLootTableModifier(new LootItemCondition[] {
                        MatchBlockCondition.builder(null, ModTags.Blocks.JARS, StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStateProperties.TREASURE, true).build()).build()
                }, new ResourceLocation(ValhelsiaStructures.MOD_ID, "treasure/jar_treasure"))
        );
    }
}
