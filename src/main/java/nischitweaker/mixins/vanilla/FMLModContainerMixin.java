package nischitweaker.mixins.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Set;

@Mixin(FMLModContainer.class)
public abstract class FMLModContainerMixin {
    @Shadow(remap = false) public abstract String getModId();

    @ModifyExpressionValue(
            method = "bindMetadata",
            at = @At(value = "FIELD", target = "Lnet/minecraftforge/fml/common/versioning/DependencyParser$DependencyInfo;requirements:Ljava/util/Set;"),
            remap = false
    )
    private Set<ArtifactVersion> nischitweaker_removeDependencies(Set<ArtifactVersion> original) {
        String modId = this.getModId();
        ArrayList<String> dependenciesToRemove = ConfigHandler.modContainers.removedDependencies.getOrDefault(modId, new ArrayList<>());

        for (String dependency : dependenciesToRemove)
            original.removeIf(vers -> vers.getLabel().equals(dependency));

        return original;
    }
}
