package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity;
import com.stal111.valhelsia_structures.tileentity.JarTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

/**
 * Client Play Net Handler Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.ClientPlayNetHandlerMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-16
 */
@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetHandlerMixin {

    @Shadow private Minecraft client;

    @Inject(at = @At(value = "HEAD"), method = "handleUpdateTileEntity")
    private void valhelsia_handleUpdateTileEntity(SUpdateTileEntityPacket packet, CallbackInfo ci) {
        BlockPos blockpos = packet.getPos();
        TileEntity tileentity = Objects.requireNonNull(this.client.world).getTileEntity(blockpos);
        int i = packet.getTileEntityType();

        if (i == 15 && tileentity instanceof JarTileEntity) {
            tileentity.read(this.client.world.getBlockState(blockpos), packet.getNbtCompound());
        }
        if (i == 16 && tileentity instanceof DungeonDoorTileEntity) {
            tileentity.read(this.client.world.getBlockState(blockpos), packet.getNbtCompound());
        }
    }
}
