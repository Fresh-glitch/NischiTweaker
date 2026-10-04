package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class ZenUtilsConfig {

    @Config.Comment("Makes #mixin Share work in ZenUtils script mixins. Fixed in ZenUtils 1.28.7.")
    @Config.Name("Fix Share Annotation (ASM Toggle)")
    public boolean fixShareAnnotation = true;
}
