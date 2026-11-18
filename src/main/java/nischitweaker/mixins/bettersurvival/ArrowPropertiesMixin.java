package nischitweaker.mixins.bettersurvival;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mujmajnkraft.bettersurvival.capabilities.extendedarrowproperties.ArrowProperties;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArrowProperties.class)
public abstract class ArrowPropertiesMixin {

    @ModifyExpressionValue(
            method = "hitEntity",
            at = @At(value = "NEW", target = "(Lnet/minecraft/item/Item;)Lnet/minecraft/item/ItemStack;"),
            remap = false
    )
    private ItemStack nischitweaker_recoverShotArrow(ItemStack original, @Local(argsOnly = true) EntityArrow arrow) {
        ItemStack stack = ((EntityArrowInvoker) arrow).nischitweaker_getArrowStack();
        return stack.isEmpty() ? original : stack;
    }
}
