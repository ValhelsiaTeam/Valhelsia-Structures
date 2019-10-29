package com.stal111.valhelsia_structures.world.structures;

import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmallCastleFeature extends Feature<NoFeatureConfig> {

    private static final ResourceLocation SMALL_CASTLE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "small_castle");
    public static Set<Block> VALID_GROUND = Sets.newHashSet(Blocks.GRASS_BLOCK, Blocks.DIRT);

    public SmallCastleFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(final IWorld worldIn, final ChunkGenerator<? extends GenerationSettings> generator, final Random rand, final BlockPos pos, final NoFeatureConfig config) {        Random random = worldIn.getRandom();
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        TemplateManager templatemanager = ((ServerWorld)worldIn.getWorld()).getSaveHandler().getStructureTemplateManager();
        Template template = templatemanager.getTemplateDefaulted(SmallCastleFeature.SMALL_CASTLE);
        BlockPos blockpos = template.transformedSize(rotation);
        PlacementSettings placementsettingsFinal = new PlacementSettings().setRotation(rotation).setRandom(random);
        IntegrityProcessor integrityprocessorFinal = new IntegrityProcessor(1.0f);
        placementsettingsFinal.func_215219_b().addProcessor(integrityprocessorFinal);
        BlockPos blockposFinal = template.getZeroPositionWithTransform(pos.add(blockpos.getX() + 8, blockpos.getY() + 4, blockpos.getZ() + 8), Mirror.NONE, rotation);
        BlockPos blockposTest = pos.add(blockpos.getX() + 4, blockpos.getY(), blockpos.getZ() + 4);
        int w = 2;
        BlockPos blockposCheckA = blockposTest.add(w, 0, w);
        BlockPos blockposCheckB = blockposTest.add(-w, 0, -w);
        if (SmallCastleFeature.VALID_GROUND.contains(worldIn.getBlockState(blockposTest.down()).getBlock()) && SmallCastleFeature.VALID_GROUND.contains(worldIn.getBlockState(blockposCheckA.down()).getBlock()) && SmallCastleFeature.VALID_GROUND.contains(worldIn.getBlockState(blockposCheckB.down()).getBlock())) {
            template.addBlocksToWorld(worldIn, blockposFinal, placementsettingsFinal, 4);
            System.out.println("Generated Small Castle at: " + blockposFinal.getX() + blockposFinal.getZ());
            System.out.println("Generated Small Castle at: " + blockposFinal.getX() + blockposFinal.getZ());
            System.out.println("Generated Small Castle at: " + blockposFinal.getX() + blockposFinal.getZ());
        }
        return true;
    }
}
