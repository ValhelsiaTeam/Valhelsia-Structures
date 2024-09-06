package com.stal111.valhelsia_structures.datagen.model;

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
    public static final ModelTemplate TEMPLATE_POST_ATTACHED = create("template_post_attached", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_1 = create("template_cut_post_1", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_1_ATTACHED = create("template_cut_post_1_attached", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_2 = create("template_cut_post_2", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_2_ATTACHED = create("template_cut_post_2_attached", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_3 = create("template_cut_post_3", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_CUT_POST_3_ATTACHED = create("template_cut_post_3_attached", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TEMPLATE_BRAZIER = create("template_brazier", TextureSlot.TOP, TextureSlot.FIRE);
    public static final ModelTemplate TEMPLATE_BRAZIER_OFF = create("template_brazier_off", TextureSlot.TOP);
    public static final ModelTemplate SLEEPING_BAG_FOOT = create("sleeping_bag_foot", TextureSlot.TEXTURE);
    public static final ModelTemplate SLEEPING_BAG_HEAD = create("sleeping_bag_head", TextureSlot.TEXTURE);
    public static final ModelTemplate SLEEPING_BAG_INVENTORY = create("sleeping_bag_inventory", TextureSlot.TEXTURE);
    public static final ModelTemplate TEMPLATE_JAR = create("template_jar", TextureSlot.TEXTURE);
    public static final ModelTemplate TEMPLATE_JAR_ROTATED = create("template_jar_rotated", TextureSlot.TEXTURE);

    public static ModelTemplate create(String name, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ValhelsiaStructures.location("block/" + name)), Optional.empty(), textureSlots);
    }

}
