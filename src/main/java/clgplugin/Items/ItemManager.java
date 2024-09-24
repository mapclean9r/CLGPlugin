package clgplugin.Items;

import clgplugin.Items.gui.mainmenu.Menu;
import clgplugin.Items.gui.mainmenu.MenuItemCollection;
import clgplugin.Items.gui.mainmenu.MenuItemSkill;
import clgplugin.Items.Weapons.YetiSword;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    //Weapons
    private static ItemStack yetiSwordItem;

    //Menu items
    private static ItemStack menuItem;
    private static ItemStack menuItemSkills;
    private static ItemStack menuItemCollection;
    public static void initItems(){
        yetiSwordItem = new YetiSword().getItemStack();

        //Menu items
        menuItem = new Menu().getItemStack();
        menuItemSkills = new MenuItemSkill().getItemStack();
        menuItemCollection = new MenuItemCollection().getItemStack();

    }

    public static ItemStack getMenuItem() {
        return menuItem;
    }

    public static ItemStack getYetiSwordItem() {
        return yetiSwordItem;
    }

    public static ItemStack getMenuItemSkills() {
        return menuItemSkills;
    }

    public static ItemStack getMenuItemCollection() {
        return menuItemCollection;
    }
}
