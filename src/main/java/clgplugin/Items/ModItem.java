package clgplugin.Items;

import org.bukkit.inventory.ItemStack;

public abstract class ModItem {
    private ItemStack itemStack;

    public ModItem(){}

    public ItemStack getItemStack() {
        return itemStack;
    }
}
