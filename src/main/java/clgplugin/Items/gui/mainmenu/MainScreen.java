package clgplugin.Items.gui.mainmenu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MainScreen implements InventoryHolder {

    private Inventory inventory;

    public MainScreen(){
        inventory = Bukkit.createInventory(this, 54, "Selection screen");
        init();
    }

    public void init(){
        ItemStack stack;
        for (int i = 0; i < 54; i++){
            stack = new MenuItemNull().getItemStack();
            inventory.setItem(i, stack);
        }

        //inp tabs

        inventory.setItem(20, new MenuItemSkill().getItemStack());
        inventory.setItem(21, new MenuItemCollection().getItemStack());
        inventory.setItem(22, new MenuItemSlayer().getItemStack());

        inventory.setItem(13, new MenuItemHead().getItemStack());
        inventory.setItem(49, new MenuItemExit().getItemStack());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
