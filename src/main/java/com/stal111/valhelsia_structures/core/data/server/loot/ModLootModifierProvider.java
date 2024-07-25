package com.stal111.valhelsia_structures.core.data.server.loot;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.valhelsia.valhelsia_core.datagen.DataProviderContext;

/**
 * Mod Loot Modifiers <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.loot.ModLootModifierProvider
 *
 * @author Valhelsia Team
 * @since 2021-05-07
 */
public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(DataProviderContext context) {
        super(context.output(), context.lookupProvider(), ValhelsiaStructures.MOD_ID);
    }

    @Override
    protected void start() {
//        add("jar_treasure",
//                new AppendLootTableModifier(new LootItemCondition[] {
//                        MatchBlockCondition.builder(null, ModTags.Blocks.JARS, StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStateProperties.TREASURE, true).build()).build()
//                }, new ResourceLocation(ValhelsiaStructures.MOD_ID, "treasure/jar_treasure"))
//        );
    }
}
