package clgplugin.Items.gui.mainmenu;

import clgplugin.Items.ModItem;
import clgplugin.Items.annotations.ItemName;
import clgplugin.Items.createItem.EnchantmentSub;
import clgplugin.Items.createItem.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@ItemName(itemname = "menuitemexit")
public class MenuItemExit extends ModItem {
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public MenuItemExit(){
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item = new ItemCreator(
                Material.BARRIER,
                1,
                enchantments,
                flags,
                ChatColor.RED+"Close",
                ""
        );
        itemStack = item.getFullItem();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}

