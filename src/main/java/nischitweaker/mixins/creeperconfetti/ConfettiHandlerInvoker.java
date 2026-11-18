package nischitweaker.mixins.creeperconfetti;

import com.sr2610.creeperconfetti.ConfettiHandler;
import net.minecraft.entity.monster.EntityCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ConfettiHandler.class)
public interface ConfettiHandlerInvoker {

    @Invoker(value = "willExplodeToConfetti", remap = false)
    boolean nischitweaker_willExplodeToConfetti();

    @Invoker(value = "damagePlayers", remap = false)
    void nischitweaker_damagePlayers(EntityCreeper creeper);
}
