package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.Codec;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public interface StructureHeightProviderType<P extends StructureHeightProvider> {

    Codec<P> codec();
}
