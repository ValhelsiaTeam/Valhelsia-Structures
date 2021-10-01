package com.stal111.valhelsia_structures.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity;
import com.stal111.valhelsia_structures.tileentity.model.DungeonDoorModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import javax.annotation.Nonnull;

/**
 * Giant Fern Tile Entity Renderer
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.renderer.GiantFernTileEntityRenderer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-22
 */
public class DungeonDoorTileEntityRenderer extends BlockEntityRenderer<DungeonDoorTileEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/dungeon_door.png");

    private final DungeonDoorModel model = new DungeonDoorModel();

    public DungeonDoorTileEntityRenderer(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(DungeonDoorTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (tileEntity.getBlockState().get(ModBlockStateProperties.DUNGEON_DOOR_PART) != DungeonDoorPart.MIDDLE_1) return;

        boolean flag = tileEntity.getWorld() != null;
        BlockState state = flag ? tileEntity.getBlockState() : ModBlocks.DUNGEON_DOOR.get().getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH);

        matrixStack.push();

        matrixStack.translate(0.5, 1.5, 0.5);

        matrixStack.rotate(Vector3f.YP.rotationDegrees(-state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));

        float leafAngle = tileEntity.getLeafAngle(partialTicks);

        model.leftDoor.rotateAngleY= -(leafAngle * ((float) Math.PI / 2F));
        model.rightDoor.rotateAngleY = (leafAngle * ((float) Math.PI / 2F));

        model.render(matrixStack, buffer.getBuffer(model.getRenderType(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
    }

    @Override
    public boolean isGlobalRenderer(@Nonnull DungeonDoorTileEntity tileEntity) {
        return true;
    }
}
