package nischitweaker.mixins.vanilla;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketEntityAction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin {

    @Shadow @Final public NetHandlerPlayClient connection;
    @Shadow private boolean serverSprintState;

    // Sprint state is only sent to the server in onUpdateWalkingPlayer, which doesn't run while riding
    @Inject(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 0)
    )
    private void nischitweaker_stopSprintingWhileRiding(CallbackInfo ci) {
        EntityPlayerSP player = (EntityPlayerSP) (Object) this;
        if (player.isSprinting()) player.setSprinting(false);
        if (this.serverSprintState) {
            this.connection.sendPacket(new CPacketEntityAction(player, CPacketEntityAction.Action.STOP_SPRINTING));
            this.serverSprintState = false;
        }
    }
}
