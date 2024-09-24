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

@ItemName(itemname = "menuitemcollector")
public class MenuItemCollection extends ModItem {
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public MenuItemCollection(){
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item = new ItemCreator(
                Material.PAINTING,
                1,
                enchantments,
                flags,
                ChatColor.GREEN+"Collections"+ChatColor.RED+" N/A",
                ChatColor.GRAY+"View all of the items available",
                ChatColor.GRAY+"Collect more of an item to",
                ChatColor.GRAY+"unlock rewards on you way to",
                ChatColor.GRAY+"becoming a master!",
                " ",
                ChatColor.YELLOW+"Collections unlocked:"+ChatColor.RED+" {N/A}",
                " ",
                ChatColor.GREEN+"Click to view!"
        );
        itemStack = item.getFullItem();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}


