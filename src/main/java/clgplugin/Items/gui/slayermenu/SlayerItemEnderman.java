package clgplugin.Items.gui.slayermenu;

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

@ItemName(itemname = "slayeritemenderman")
public class SlayerItemEnderman extends ModItem {
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public SlayerItemEnderman(){
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item = new ItemCreator(
                Material.ENDER_PEARL,
                1,
                enchantments,
                flags,
                ChatColor.GREEN+"Enderman",
                ChatColor.GRAY+"Fight Enderman"
        );
        itemStack = item.getFullItem();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
