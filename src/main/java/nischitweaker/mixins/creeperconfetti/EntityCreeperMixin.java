package nischitweaker.mixins.creeperconfetti;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sr2610.creeperconfetti.ConfigHandler;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import nischitweaker.network.CreeperConfettiPacket;
import nischitweaker.network.PacketHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityCreeper.class)
public abstract class EntityCreeperMixin {

    @WrapMethod(method = "explode")
    private void nischitweaker_explodeToConfetti(Operation<Void> original) {
        EntityCreeper creeper = (EntityCreeper) (Object) this;
        World world = creeper.world;
        ConfettiHandlerInvoker handler = (ConfettiHandlerInvoker) CreeperConfettiPacket.confettiHandler;
        boolean canConfetti = !world.isRemote
                && (!nischitweaker.config.ConfigHandler.creeperconfetti.onlyWhenTargetingPlayer || creeper.getAttackTarget() instanceof EntityPlayer);
        if (!canConfetti || !handler.nischitweaker_willExplodeToConfetti()) {
            original.call();
            return;
        }

        world.playSound(null, creeper.posX, creeper.posY, creeper.posZ, SoundEvents.ENTITY_FIREWORK_TWINKLE, SoundCategory.BLOCKS, 0.5F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
        PacketHandler.INSTANCE.sendToAllTracking(new CreeperConfettiPacket(creeper.getEntityId()), creeper);
        creeper.setDead();
        if (ConfigHandler.damagesPlayers) handler.nischitweaker_damagePlayers(creeper);
    }
}
