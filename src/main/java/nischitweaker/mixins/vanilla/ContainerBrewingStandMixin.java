package nischitweaker.mixins.vanilla;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ContainerBrewingStand.class)
public abstract class ContainerBrewingStandMixin extends Container {

    // Vanilla only shift-clicks potions with count 1 into the bottle slots.
    // mergeItemStack fills one empty bottle slot per call and slotClick repeats transferStackInSlot, so stacks get spread one per slot
    @Definition(id = "getCount", method = "Lnet/minecraft/item/ItemStack;getCount()I")
    @Expression("?.getCount() == 1")
    @ModifyExpressionValue(method = "transferStackInSlot", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean nischitweaker_allowStackedPotions(boolean isSingle) {
        return true;
    }

    // Fuel slot before ingredient slot, so blaze powder goes into the ingredient slot only once the fuel slot is full
    @WrapOperation(
            method = "transferStackInSlot",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;isItemValid(Lnet/minecraft/item/ItemStack;)Z", ordinal = 0)
    )
    private boolean nischitweaker_preferFuelSlot(Slot ingredientSlot, ItemStack stack, Operation<Boolean> original) {
        return original.call(ingredientSlot, stack) && !this.nischitweaker_fuelSlotAccepts(stack);
    }

    @Unique
    private boolean nischitweaker_fuelSlotAccepts(ItemStack stack) {
        Slot fuelSlot = this.getSlot(4);
        if (!fuelSlot.isItemValid(stack)) return false;
        ItemStack fuel = fuelSlot.getStack();
        if (fuel.isEmpty()) return true;
        return ItemHandlerHelper.canItemStacksStack(fuel, stack) && fuel.getCount() < Math.min(fuelSlot.getItemStackLimit(fuel), fuel.getMaxStackSize());
    }
}
