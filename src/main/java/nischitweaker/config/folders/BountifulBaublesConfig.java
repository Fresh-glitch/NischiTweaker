package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class BountifulBaublesConfig {

    @Config.Comment({
            "Fix crafting recipes using existing baubles changing the bauble modifier.",
            "In RLCraft this happens when changing backpack colors."
    })
    @Config.Name("Fix Crafting Changing Modifier (MixinToggle)")
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.bountifulbaubles.craftingmodifierfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "bountifulbaubles", desired = true, warnIngame = false, reason = "Compat for Bountiful Baubles")
    public boolean craftModifierFix = true;

    @Config.Comment("Fixes a potential exploit in Bountiful Baubles")
    @Config.Name("Fix Reforger Exploit (MixinToggle)")
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.bountifulbaubles.reforgerunequip.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "bountifulbaubles", desired = true, warnIngame = false, reason = "Compat for Bountiful Baubles")
    public boolean reforgerUnequipFix = true;

    @Config.Comment({
            "Fix the bonuses of Ring of Overclocking and Wrath Pendant staying after unequipping them.",
            "Also cleans up already stuck bonuses on existing players."
    })
    @Config.Name("Fix Bauble Item Modifiers Not Removed (MixinToggle)")
    @MixinConfig.MixinToggle(mixinJson = "mixin.nischitweaker.bountifulbaubles.itemmodifierremoval.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "bountifulbaubles", desired = true, warnIngame = false, reason = "Compat for Bountiful Baubles")
    public boolean itemModifierRemovalFix = true;
}
