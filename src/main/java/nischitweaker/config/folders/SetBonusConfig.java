package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class SetBonusConfig {

    @Config.Comment("Fix rare concurrency crash when rendering setbonus tooltips")
    @Config.Name("Concurrency Fix (MixinToggle)")
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.setbonus.concurrencyfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "setbonus", desired = true, warnIngame = false, reason = "Compat for SetBonus")
    public boolean concurrencyFix = true;

    @Config.Comment("Fix NoSuchMethodError crash (PrefixedSearchTree.getMode) when SetBonus refreshes JEI tooltips with newer JEI versions")
    @Config.Name("JEI SearchMode Fix (MixinToggle)")
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.setbonus.jeisearchmodefix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "setbonus", desired = true, warnIngame = false, reason = "Compat for SetBonus")
    @MixinConfig.CompatHandling(modid = "jei", targetVersionRange = "[4.24.0.1042,)", desired = true, warnIngame = false, reason = "Compat for SetBonus x JEI 4.24.0.1042+ (moved SearchMode out of Config)")
    public boolean jeiSearchModeFix = true;
}
