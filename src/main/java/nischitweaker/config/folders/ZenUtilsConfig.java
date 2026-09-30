package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class ZenUtilsConfig {

    @Config.Comment("Makes ZenUtils not depend on ConfigAnytime.")
    @Config.Name("Remove ConfigAnytime Dependency (ASM Toggle)")
    public boolean removeConfigAnytimeDependency = true;
}
