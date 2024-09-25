package clgplugin.Items;

import clgplugin.Items.gui.mainmenu.Menu;
import clgplugin.Items.Weapons.YetiSword;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    //Weapons
    private static ItemStack yetiSwordItem;

    //Menu items
    private static ItemStack menuItem;

    public static void initItems(){
        //Items
        yetiSwordItem = new YetiSword().getItemStack();

        //Menu items
        menuItem = new Menu().getItemStack();

    }

    public static ItemStack getMenuItem() {
        return menuItem;
    }

    public static ItemStack getYetiSwordItem() {
        return yetiSwordItem;
    }

}
