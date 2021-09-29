package com.stal111.valhelsia_structures.world.structures.start;

import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;

/**
 * Valhelsia Structure Start
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.start.ValhelsiaStructureStart
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-05-12
 */
public abstract class ValhelsiaStructureStart<C extends FeatureConfiguration> extends StructureStart<C> {

    public ValhelsiaStructureStart(StructureFeature<C> structure, int chunkX, int chunkZ, BoundingBox boundingBox, int references, long seed) {
        super(structure, chunkX, chunkZ, boundingBox, references, seed);
    }

    @Override
    protected void recalculateStructureSize() {
        super.recalculateStructureSize();

        if (!(this.getStructure() instanceof AbstractValhelsiaStructure)) {
            return;
        }
        AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) this.getStructure();

        if (structure.hasMargin()) {
            int margin = structure.getMargin();

            this.bounds.minX -= margin;
            this.bounds.minY -= margin;
            this.bounds.minZ -= margin;
            this.bounds.maxX += margin;
            this.bounds.maxY += margin;
            this.bounds.maxZ += margin;
        }
    }
}
