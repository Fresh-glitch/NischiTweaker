package nischitweaker.mixins.creeperconfetti;

import com.sr2610.creeperconfetti.ConfettiHandler;
import net.minecraft.entity.monster.EntityCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ConfettiHandler.class)
public interface ConfettiHandlerClientInvoker {

    @Invoker(value = "spawnParticles", remap = false)
    void nischitweaker_spawnParticles(EntityCreeper creeper);
}
