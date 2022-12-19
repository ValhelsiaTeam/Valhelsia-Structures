package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Mod Structure Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.data.ModStructureTagsProvider
 *
 * @author Valhelsia Team
 * @since 2022-03-20
 */
public class ModStructureTagsProvider extends StructureTagsProvider {

    public ModStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        this.tag(ModTags.Structures.ON_SPAWNER_DUNGEON_EXPLORER_MAPS).add(ModStructures.SPAWNER_DUNGEON);
        this.tag(ModTags.Structures.ON_CASTLE_EXPLORER_MAPS).add(ModStructures.CASTLE);
    }
}
