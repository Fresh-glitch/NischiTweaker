package nischitweaker.features.bookwyrmbucket;

import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nischitweaker.config.ConfigHandler;

@SideOnly(Side.CLIENT)
public class BucketedBookWyrmClient {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(BucketedBookWyrm.ITEM, 0, new ModelResourceLocation(BucketedBookWyrm.ITEM.getRegistryName(), "inventory"));
    }

    public static World getClientWorld() {
        return Minecraft.getMinecraft().world;
    }

    public static boolean showStats() {
        switch (ConfigHandler.defiledlands.bookwyrmBucket.statsTooltip) {
            case ALWAYS: return true;
            case NEVER: return false;
            default:
                EntityPlayer player = Minecraft.getMinecraft().player;
                if (player == null) return false;
                for (ItemStack stack : player.inventory.mainInventory) if (stack.getItem() == ModItems.bookWyrmAnalyzer) return true;
                for (ItemStack stack : player.inventory.offHandInventory) if (stack.getItem() == ModItems.bookWyrmAnalyzer) return true;
                return false;
        }
    }
}
