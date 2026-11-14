package nischitweaker.mixins.setbonus;

import mezz.jei.config.SearchMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//PrefixedSearchTree is package-private, so it can only be targeted by name
@Mixin(targets = "mezz.jei.ingredients.PrefixedSearchTree", remap = false)
public interface PrefixedSearchTreeInvoker {
    @Invoker("getMode")
    SearchMode nischitweaker$invokeGetMode();
}
