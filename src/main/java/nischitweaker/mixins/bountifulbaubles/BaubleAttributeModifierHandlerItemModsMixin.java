package nischitweaker.mixins.bountifulbaubles;

import cursedflames.bountifulbaubles.baubleeffect.BaubleAttributeModifierHandler;
import cursedflames.bountifulbaubles.item.ItemAmuletSinWrath;
import cursedflames.bountifulbaubles.item.ItemCharmHermesWings;
import cursedflames.bountifulbaubles.item.ItemRingOverclocking;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaubleAttributeModifierHandler.class)
public abstract class BaubleAttributeModifierHandlerItemModsMixin {

    // Item-intrinsic modifiers get reapplied right after this by EventHandler.playerTick for baubles that are still worn
    @Inject(method = "removeAllModifiers", at = @At("TAIL"), remap = false)
    private static void nischitweaker_removeItemModifiers(EntityPlayer player, CallbackInfo ci) {
        IAttributeInstance speed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        speed.removeModifier(ItemRingOverclocking.SPEED_UUID);
        speed.removeModifier(ItemCharmHermesWings.SPEED_UUID);
        player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(ItemAmuletSinWrath.DAMAGE_UUID);
    }
}
