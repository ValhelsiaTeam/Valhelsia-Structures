package com.stal111.valhelsia_structures.data.server.loot;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.valhelsia.valhelsia_core.init.ValhelsiaLootModifiers;
import net.valhelsia.valhelsia_core.loot.conditions.MatchBlockCondition;
import net.valhelsia.valhelsia_core.loot.modifiers.AppendLootTableModifier;

/**
 * Mod Loot Modifiers
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.loot.ModLootModifierProvider
 *
 * @author Valhelsia Team
 * @version 16.2.0
 * @since 2021-05-07
 */
public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(DataGenerator gen) {
        super(gen, ValhelsiaStructures.MOD_ID);
    }

    @Override
    protected void start() {
        add("jar_treasure",
                ValhelsiaLootModifiers.APPEND_LOOT_MODIFIER.get(),
                new AppendLootTableModifier(new LootItemCondition[] {
                        MatchBlockCondition.builder(null, ModTags.Blocks.JARS, StatePropertiesPredicate.Builder.newBuilder().withBoolProp(ModBlockStateProperties.TREASURE, true).build()).build()
                }, new ResourceLocation(ValhelsiaStructures.MOD_ID, "treasure/jar_treasure"))
        );
    }
}
