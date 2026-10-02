package nischitweaker.mixins.vanilla;

import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModMetadata;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(FMLModContainer.class)
public abstract class FMLModContainerMixin {
    @Shadow(remap = false) public abstract String getModId();
    @Shadow(remap = false) private ModMetadata modMetadata;

    @Inject(
            method = "bindMetadata",
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;trace(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", ordinal = 0),
            remap = false
    )
    private void nischitweaker_removeDependencies(MetadataCollection mc, CallbackInfo ci) {
        for (String dependency : ConfigHandler.modContainers.removedDependencies.getOrDefault(this.getModId(), new ArrayList<>())) {
            modMetadata.requiredMods.removeIf(vers -> vers.getLabel().equals(dependency));
            modMetadata.dependants.removeIf(vers -> vers.getLabel().equals(dependency));
            modMetadata.dependencies.removeIf(vers -> vers.getLabel().equals(dependency));
        }
    }
}
