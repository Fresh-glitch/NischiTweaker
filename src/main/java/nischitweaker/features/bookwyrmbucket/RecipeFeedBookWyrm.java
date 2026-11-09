package nischitweaker.features.bookwyrmbucket;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Bucketed Book Wyrm + any amount of enchanted books -> fed Bucketed Book Wyrm.
 * Burping happens while the wyrm is in a player inventory, see ItemBucketedBookWyrm#onUpdate.
 */
public class RecipeFeedBookWyrm extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    /** Wyrm in the grid with the total value of all fed books added, or empty if the grid doesn't fit */
    private static ItemStack getFedWyrm(InventoryCrafting inv) {
        ItemStack wyrm = ItemStack.EMPTY;
        int fedValue = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() == BucketedBookWyrm.ITEM) {
                if (!wyrm.isEmpty()) return ItemStack.EMPTY;
                wyrm = stack;
            } else if (stack.getItem() == Items.ENCHANTED_BOOK) {
                int value = ItemBucketedBookWyrm.getBookValue(stack);
                if (value <= 0) return ItemStack.EMPTY;
                fedValue += value;
            } else return ItemStack.EMPTY;
        }
        if (wyrm.isEmpty() || fedValue <= 0) return ItemStack.EMPTY;
        return ItemBucketedBookWyrm.feed(wyrm, fedValue);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        return !getFedWyrm(inv).isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return getFedWyrm(inv);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
