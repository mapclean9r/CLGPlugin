package clgplugin.Items.gui.slayermenu;

import clgplugin.Items.ModItem;
import clgplugin.Items.gui.mainmenu.MenuItemExit;
import clgplugin.Items.gui.mainmenu.MenuItemNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlayerScreen extends ModItem implements InventoryHolder {

    private Inventory inventory;

    public SlayerScreen(){
        inventory = Bukkit.createInventory(this, 36, "Selection screen");
        init();
    }

    public void init(){
        ItemStack stack;
        for (int i = 0; i < 36; i++){
            stack = new MenuItemNull().getItemStack();
            inventory.setItem(i, stack);
        }

        //inp tabs

        inventory.setItem(10, new SlayerItemZombie().getItemStack());
        inventory.setItem(11, new SlayerItemSpider().getItemStack());
        inventory.setItem(12, new SlayerItemSkeleton().getItemStack());
        inventory.setItem(13, new SlayerItemEnderman().getItemStack());
        inventory.setItem(31, new MenuItemExit().getItemStack());
    }

    private ItemStack createItem(String name, Material material, String... lore){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        List<String> lo = new ArrayList<>(Arrays.asList(lore));
        itemMeta.setLore(lo);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
