package nischitweaker.mixins.bountifulbaubles;

import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.container.SlotBauble;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import cursedflames.bountifulbaubles.block.GenericContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GenericContainer.class)
public abstract class GenericContainerMixin {

    @WrapMethod(method = "transferStackInSlot")
    private ItemStack nischitweaker_unequipShiftClickedBauble(EntityPlayer playerIn, int index, Operation<ItemStack> original) {
        Slot slot = ((Container) (Object) this).inventorySlots.get(index);
        if (!(slot instanceof SlotBauble) || !slot.getHasStack()) return original.call(playerIn, index);

        ItemStack before = slot.getStack().copy();
        ItemStack result = original.call(playerIn, index);

        if (!slot.getHasStack()
                && !((IBaublesItemHandler) ((SlotBauble) slot).getItemHandler()).isEventBlocked()
                && before.hasCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null)) {
            IBauble bauble = before.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null);
            if (bauble != null) bauble.onUnequipped(before, playerIn);
        }
        return result;
    }
}
