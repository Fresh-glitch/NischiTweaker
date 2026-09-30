package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

import java.util.*;

public class ModContainerConfig {

    @Config.Comment({
            "Map of modids to dependencies that should be removed.",
            "This will not magically remove actual dependencies, just the declaration in @Mod or mcmod.info"
    })
    @Config.Name("Removed declared Dependencies")
    public Map<String, ArrayList<String>> removedDependencies = new LinkedHashMap<>();
    private void initRemovedDependencies() {
        removedDependencies.put("zenutils", new ArrayList<>(Collections.singletonList("configanytime")));
        removedDependencies.put("distanthorizons", new ArrayList<>(Collections.singletonList("mixinbooter")));
    }

    @Config.Comment("List of classes to replace ConfigAnytime.register with zenutils ConfigAnytimeAnytime.register")
    @Config.Name("ConfigAnytime Dependency Replacements (ASM Toggle)")
    public List<String> configAnytimeClassPatches = new ArrayList<>(Arrays.asList(
            "youyihj.zenutils.impl.core.Configuration",
            "git.jbredwards.baubleye.PatchConfigs"
    ));

    public ModContainerConfig() {
        initRemovedDependencies();
    }
}
