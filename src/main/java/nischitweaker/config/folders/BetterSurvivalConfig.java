package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class BetterSurvivalConfig {

    @Config.Comment("Arrow Recovery returns the arrow that was shot (tipped, spectral, modded) instead of always a vanilla arrow.")
    @Config.Name("Arrow Recovery Returns Shot Arrow (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.bettersurvival.arrowrecoverytype.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "mujmajnkraftsbettersurvival", desired = true, warnIngame = false, reason = "Compat for Better Survival")
    public boolean arrowRecoveryShotArrow = true;
}
