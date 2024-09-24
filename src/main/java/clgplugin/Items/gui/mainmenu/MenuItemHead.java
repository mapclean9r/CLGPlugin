package clgplugin.Items.gui.mainmenu;

import clgplugin.Items.ModItem;
import clgplugin.Items.annotations.ItemName;
import clgplugin.Items.createItem.EnchantmentSub;
import clgplugin.Items.createItem.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

@ItemName(itemname = "menuitemhead")
public class MenuItemHead extends ModItem implements Listener {
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();
    public MenuItemHead(){
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item = new ItemCreator(
                Material.PLAYER_HEAD,
                1,
                enchantments,
                flags,
                ChatColor.GREEN+"Your Stats",
                ChatColor.GRAY+"Stats",
                ChatColor.GRAY+"Stats",
                ChatColor.GRAY+"Stats",
                ChatColor.GRAY+"Stats",
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


