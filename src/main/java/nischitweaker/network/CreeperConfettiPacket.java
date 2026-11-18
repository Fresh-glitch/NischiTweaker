package nischitweaker.network;

import com.sr2610.creeperconfetti.ConfettiHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nischitweaker.mixins.creeperconfetti.ConfettiHandlerClientInvoker;

public class CreeperConfettiPacket implements IMessage {
    // The instance Creeper Confetti registers to the event bus
    public static ConfettiHandler confettiHandler;

    private int entityId;

    public CreeperConfettiPacket() {}

    public CreeperConfettiPacket(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
    }

    public static class Handler implements IMessageHandler<CreeperConfettiPacket, IMessage> {
        @Override
        public IMessage onMessage(CreeperConfettiPacket message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                if (mc.world == null) return;
                Entity entity = mc.world.getEntityByID(message.entityId);
                if (entity instanceof EntityCreeper)
                    ((ConfettiHandlerClientInvoker) confettiHandler).nischitweaker_spawnParticles((EntityCreeper) entity);
            });
            return null;
        }
    }
}
