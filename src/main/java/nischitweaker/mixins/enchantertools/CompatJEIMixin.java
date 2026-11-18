package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.sirsquidly.enchanter_tools.compat.CompatJEI;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IIngredientType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CompatJEI.class)
public abstract class CompatJEIMixin {

    @WrapWithCondition(
            method = "addInformation",
            at = @At(value = "INVOKE", target = "Lmezz/jei/api/IModRegistry;addIngredientInfo(Ljava/lang/Object;Lmezz/jei/api/recipe/IIngredientType;[Ljava/lang/String;)V"),
            remap = false
    )
    private boolean nischitweaker_skipDisabledItems(IModRegistry registry, Object ingredient, IIngredientType<?> type, String[] descriptionKeys) {
        return !(ingredient instanceof ItemStack) || !((ItemStack) ingredient).isEmpty();
    }
}
