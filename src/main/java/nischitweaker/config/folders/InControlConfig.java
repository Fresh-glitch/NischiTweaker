package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class InControlConfig {
    @Config.Comment("If your installed profile is in a path containing \"curseforge\", all mobs would be matched to forge -> minecraft modid by incontrols modid check. This fixes it.")
    @Config.Name("Fix mod rules")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.nischitweaker.incontrol.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "incontrol", desired = true, warnIngame = false, reason = "Fix for InControl")
    public boolean fixGettingModid = true;
}
