package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class BaubleyElytraConfig {

    @Config.Comment("Makes Baubley Elytra not depend on ConfigAnytime but on ZenUtils.")
    @Config.Name("Replace ConfigAnytime Dependency (ASM Toggle)")
    public boolean removeConfigAnytimeDependency = true;
}
