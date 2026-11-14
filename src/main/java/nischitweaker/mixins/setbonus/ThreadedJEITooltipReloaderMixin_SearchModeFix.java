package nischitweaker.mixins.setbonus;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import mezz.jei.config.SearchMode;
import mezz.jei.ingredients.ThreadedJEITooltipReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * SetBonus ships its own mezz.jei.ingredients.ThreadedJEITooltipReloader, compiled against old JEI where
 * PrefixedSearchTree.getMode() returned Config$SearchMode. Since JEI 4.24.0.1042 it returns mezz.jei.config.SearchMode,
 * so needsReload() throws NoSuchMethodError whenever SetBonus syncs bonuses to the client (world join, dimension change).
 */
@Mixin(value = ThreadedJEITooltipReloader.class, remap = false)
public abstract class ThreadedJEITooltipReloaderMixin_SearchModeFix {
    @Inject(method = "needsReload", at = @At("HEAD"), cancellable = true)
    private static void nischitweaker_needsReloadNewSearchMode(Char2ObjectMap<?> prefixedSearchTrees, CallbackInfoReturnable<Boolean> cir) {
        //Same as original: true if the '#' (tooltip) search tree exists and its search mode isn't DISABLED
        Object tooltipTree = prefixedSearchTrees.get('#');
        cir.setReturnValue(tooltipTree != null && ((PrefixedSearchTreeInvoker) tooltipTree).nischitweaker$invokeGetMode() != SearchMode.DISABLED);
    }
}
