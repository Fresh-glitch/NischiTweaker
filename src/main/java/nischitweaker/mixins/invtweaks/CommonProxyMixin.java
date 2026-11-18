package nischitweaker.mixins.invtweaks;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import invtweaks.forge.CommonProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CommonProxy.class)
public abstract class CommonProxyMixin {

    // Server only: JEI's client config can't be loaded there since JEI 4.24.0.1042
    @WrapWithCondition(
            method = "postInit",
            at = @At(value = "INVOKE", target = "Linvtweaks/integration/ItemListSorter;LinkJEITComparator()V"),
            remap = false
    )
    private boolean nischitweaker_dontLinkJEIOnServer() {
        return false;
    }
}
