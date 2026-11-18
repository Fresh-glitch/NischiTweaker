package nischitweaker.mixins.bettersurvival;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityArrow.class)
public interface EntityArrowInvoker {

    @Invoker("getArrowStack")
    ItemStack nischitweaker_getArrowStack();
}
