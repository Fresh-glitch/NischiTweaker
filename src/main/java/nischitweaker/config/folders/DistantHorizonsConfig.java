package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class DistantHorizonsConfig {

    @Config.Comment("Makes DistantHorizons not depend on MixinBooter.")
    @Config.Name("Remove MixinBooter Dependency (ASM Toggle)")
    public boolean removeMixinBooterDependency = true;
}
