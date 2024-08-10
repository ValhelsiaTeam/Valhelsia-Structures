package com.stal111.valhelsia_structures.data.model;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

/**
 * @author Vahelsia Team - stal111
 * @since 28.07.2024
 */
public class ModModelTemplates {

    public static final ModelTemplate TEMPLATE_POST = create("template_post", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_ATTACHED_POST = create("template_post_attached", TextureSlot.SIDE, TextureSlot.END);

    public static ModelTemplate create(String name, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ValhelsiaStructures.location("block/" + name)), Optional.empty(), textureSlots);
    }

}
