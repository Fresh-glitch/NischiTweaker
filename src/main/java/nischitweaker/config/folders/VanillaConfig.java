package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class VanillaConfig {

    @Config.Comment("Adds a chance to replace a librarians enchanted book trade with a max enchanted inkwell instead.")
    @Config.Name("Enchanted Inkwell Villager Trades (MixinToggle)")
    @MixinConfig.MixinToggle(earlyMixin = "mixins.nischitweaker.vanilla.inkwell.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", desired = true, warnIngame = false, reason = "Enchanted Inkwell mixin requires Enchanter Tools")
    @Order(0)
    public boolean enableEnchantedInkwellMixin = true;

    @Config.Comment("Chance to replace enchanted book trades with enchanted inkwells.")
    @Config.Name("Enchanted Inkwell Chance")
    @Config.RangeDouble(min = 0, max = 1)
    @Config.SlidingOption
    @Order(1)
    public double inkwellReplaceChance = 0.01;
}