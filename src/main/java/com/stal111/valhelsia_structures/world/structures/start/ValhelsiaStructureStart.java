package com.stal111.valhelsia_structures.world.structures.start;

import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;

/**
 * Valhelsia Structure Start
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.start.ValhelsiaStructureStart
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-05-12
 */
public abstract class ValhelsiaStructureStart<C extends IFeatureConfig> extends StructureStart<C> {

    public ValhelsiaStructureStart(Structure<C> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
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
