package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class CreeperConfettiConfig {

    @Config.Comment({
            "Creeper Confetti rolls its confetti chance separately on client and server.",
            "That makes creepers vanish without confetti, or show confetti and still explode.",
            "This rolls only on the server when the creeper explodes and sends the confetti to clients."
    })
    @Config.Name("Fix Confetti Desync (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.creeperconfetti.desyncfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "creeperconfetti", desired = true, warnIngame = false, reason = "Compat for Creeper Confetti")
    public boolean fixConfettiDesync = true;
}
