package com.stal111.valhelsia_structures.world.structures;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModStructurePieces;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

/**
 * Small Castle Piece
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.structures.SmallCastlePiece
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
public class TowerRuinPiece {

    private static final BlockPos STRUCTURE_OFFSET = new BlockPos(0, 0, 0);
    private static final ResourceLocation LOCATION = new ResourceLocation(ValhelsiaStructures.MOD_ID, "tower_ruin");

    public static void addPieces(ChunkGenerator<?> generator, TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces) {
        TowerRuinPiece.Piece piece = new TowerRuinPiece.Piece(templateManager, LOCATION, pos, rotation);
        pieces.add(piece);
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;
        private final Rotation rotation;

        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation) {
            super(ModStructurePieces.TOWER_RUIN, 0);
            this.templateLocation = templateLocation;
            this.templatePosition = pos;
            this.rotation = rotation;
            this.setup(templateManager);
        }

        public Piece(TemplateManager templateManager, CompoundNBT compound) {
            super(ModStructurePieces.TOWER_RUIN, compound);
            this.templateLocation = new ResourceLocation(compound.getString("Template"));
            this.rotation = Rotation.valueOf(compound.getString("Rot"));
            this.setup(templateManager);
        }

        private void setup(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.templateLocation);

            PlacementSettings placementData = new PlacementSettings()
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(STRUCTURE_OFFSET)
                    .addProcessor(Processors.RED_GLASS_AND_STRUCTURE_BLOCK);

            this.setup(template, this.templatePosition, placementData);
        }

        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.templateLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(@Nonnull String function, @Nonnull BlockPos position, @Nonnull IWorld world, @Nonnull Random rand, @Nonnull MutableBoundingBox sbb) {
        }

        @Override
        public boolean addComponentParts(@Nonnull IWorld world, @Nonnull Random random, @Nonnull MutableBoundingBox bounds, ChunkPos chunkPos) {
            BlockPos templateOrigin = this.templatePosition;
            boolean result = super.addComponentParts(world, random, bounds, chunkPos);

            this.templatePosition = templateOrigin;
            return result;
        }
    }
}