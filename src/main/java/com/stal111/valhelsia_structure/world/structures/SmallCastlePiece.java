package com.stal111.valhelsia_structure.world.structures;

import java.util.List;
import java.util.Random;

import com.stal111.valhelsia_structure.ValhelsiaStructure;
import com.stal111.valhelsia_structure.init.ModStructurePieces;
import com.stal111.valhelsia_structure.utils.StructureUtils;
import com.stal111.valhelsia_structure.world.template.Processors;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.storage.loot.LootTables;

import javax.annotation.Nonnull;

/**
 * Small Castle Piece
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.structures.SmallCastlePiece
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
public class SmallCastlePiece {
    private static final ResourceLocation LOCATION = new ResourceLocation(ValhelsiaStructure.MOD_ID, "small_castle");

    @SuppressWarnings("WeakerAccess")
    public static void addCastlePieces(ChunkGenerator<?> generator, TemplateManager templateManager, BlockPos position, Rotation rotation, List<StructurePiece> pieces) {
        pieces.add(new SmallCastlePiece.Piece(templateManager, LOCATION, position, rotation));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;
        private final Rotation rotation;

        @SuppressWarnings("WeakerAccess")
        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos position, Rotation rotation) {
            super(ModStructurePieces.SMALL_CASTLE, 0);
            this.templateLocation = templateLocation;
            this.templatePosition = position;
            this.rotation = rotation;
            this.setup(templateManager);
        }

        public Piece(TemplateManager templateManager, CompoundNBT compound) {
            super(ModStructurePieces.SMALL_CASTLE, compound);
            this.templateLocation = new ResourceLocation(compound.getString("Template"));
            this.rotation = Rotation.valueOf(compound.getString("Rot"));
            this.setup(templateManager);
        }

        private void setup(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.templateLocation);

            PlacementSettings placementData = new PlacementSettings()
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .addProcessor(Processors.RED_GLASS_AND_STRUCTURE_BLOCK);

            this.setup(template, this.templatePosition, placementData);
        }

        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.templateLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        protected void handleDataMarker(@Nonnull String function, @Nonnull BlockPos position, @Nonnull IWorld world, @Nonnull Random rand, @Nonnull MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                world.setBlockState(position, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(position.down());
                if (tileentity instanceof ChestTileEntity) {
                    // TODO: Change the loot table.
                    ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_ABANDONED_MINESHAFT, rand.nextLong());
                }
            }
        }

        public boolean addComponentParts(@Nonnull IWorld world, @Nonnull Random random, @Nonnull MutableBoundingBox bounds, ChunkPos chunkPos) {

            // Use the lowest height of the four corners of the structure.
            int height = Math.max(60, StructureUtils.getLowestHeight(world,
                    this.templatePosition.getX(),
                    this.templatePosition.getZ(),
                    bounds.getXSize(),
                    bounds.getZSize()));

            BlockPos templateOrigin = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, height - 90, 0);
            boolean result = super.addComponentParts(world, random, bounds, chunkPos);
            this.templatePosition = templateOrigin;

            return result;
        }
    }
}