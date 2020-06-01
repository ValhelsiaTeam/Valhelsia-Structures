package com.stal111.valhelsia_structures.recipe;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Map;

/**
 * Axe Crafting Recipe
 * Valhelsia Structures - com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe
 *
 * A crafting recipe that can use any axe (that extends AxeItem) and a number of other ingredients in a shapeless
 * form. The axe loses one durability per craft but is returned.
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-06-01
 */
public class AxeCraftingRecipe extends SpecialRecipe {

    protected static Map<Block, Block> LOG_POST_MAP = null;

    public static final IRecipeType<AxeCraftingRecipe> RECIPE_TYPE = new IRecipeType<AxeCraftingRecipe>() {
        @Override
        public String toString() {
            return ValhelsiaStructures.MOD_ID + ":axe_crafting";
        }
    };

    public AxeCraftingRecipe(ResourceLocation idIn) {
        super(idIn);
        LOG_POST_MAP = (new ImmutableMap.Builder<Block, Block>()).put(Blocks.OAK_LOG, ModBlocks.OAK_POST.get()).put(Blocks.SPRUCE_LOG, ModBlocks.SPRUCE_POST.get()).put(Blocks.BIRCH_LOG, ModBlocks.BIRCH_POST.get()).put(Blocks.JUNGLE_LOG, ModBlocks.JUNGLE_POST.get()).put(Blocks.ACACIA_LOG, ModBlocks.ACACIA_POST.get()).put(Blocks.DARK_OAK_LOG, ModBlocks.DARK_OAK_POST.get()).build();
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        int axeSlot = -1;
        Block block = null;

        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            if (inv.getStackInSlot(slot).getItem() instanceof AxeItem) {
                axeSlot = slot;
                break;
            }
        }

        if (axeSlot == -1) {
            return false;
        }

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (i != axeSlot && !stack.isEmpty()) {
                if (isValidBlock(stack) && (block == null || Block.getBlockFromItem(stack.getItem()) == block)) {
                    block = Block.getBlockFromItem(stack.getItem());
                } else {
                    return false;
                }
            }
        }

        return block != null;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        Block block = null;
        int logCount = 0;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (isValidBlock(stack)) {
                block = Block.getBlockFromItem(stack.getItem());
                logCount++;
            }
        }

        return new ItemStack(LOG_POST_MAP.get(block), logCount * 2);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        int logCount = 0;

        for(int i = 0; i < nonnulllist.size(); i++) {
            if (isValidBlock(inv.getStackInSlot(i))) {
                logCount++;
            }
        }


        for(int i = 0; i < nonnulllist.size(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.getItem() instanceof AxeItem) {
                ItemStack stack1 = stack.copy();
                stack1.setDamage(stack1.getDamage() + logCount);

                if (!(stack1.getDamage() >= stack1.getMaxDamage())) {
                    nonnulllist.set(i, stack1);
                }
            }
        }

        return nonnulllist;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
    }

    private boolean isValidBlock(ItemStack stack) {
        return LOG_POST_MAP.containsKey(Block.getBlockFromItem(stack.getItem()));
    }
}
