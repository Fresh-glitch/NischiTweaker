package nischitweaker.mixins.creeperconfetti;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sr2610.creeperconfetti.ConfettiHandler;
import net.minecraftforge.event.entity.living.LivingEvent;
import nischitweaker.network.CreeperConfettiPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Creeper Confetti rolls separately on client and server, so the confetti particles and the creeper not exploding don't line up.
 * Replaced by EntityCreeperMixin.
 */
@Mixin(ConfettiHandler.class)
public abstract class ConfettiHandlerMixin {

    @WrapMethod(method = "creeperExplodeEvent", remap = false)
    private void nischitweaker_disableHandler(LivingEvent.LivingUpdateEvent event, Operation<Void> original) {
    }

    @Inject(method = "<init>", at = @At("RETURN"), remap = false)
    private void nischitweaker_storeInstance(CallbackInfo ci) {
        CreeperConfettiPacket.confettiHandler = (ConfettiHandler) (Object) this;
    }
}
