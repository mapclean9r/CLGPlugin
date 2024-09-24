package clgplugin.Items.gui.mainmenu;

import clgplugin.Items.ItemManager;
import clgplugin.Items.ModItem;
import clgplugin.Items.annotations.ItemName;
import clgplugin.Items.createItem.EnchantmentSub;
import clgplugin.Items.createItem.ItemCreator;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ItemName(itemname = "menu")
public class Menu extends ModItem implements Listener{
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public Menu(){
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item = new ItemCreator(
                Material.NETHER_STAR,
                1,
                enchantments,
                flags,
                ChatColor.GREEN+"Player Menu"+ChatColor.GRAY+" (click)",
                " ", ChatColor.GREEN+"Click to open!"
        );
        itemStack = item.getFullItem();
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            if (Objects.equals(Objects.requireNonNull(event.getItem()).getItemMeta(), ItemManager.getMenuItem().getItemMeta())){
                Player player = event.getPlayer();
                MainScreen gui = new MainScreen();
                player.openInventory(gui.getInventory());
                player.sendMessage("Clicked §3"+ Objects.requireNonNull(event.getItem().getItemMeta()).getDisplayName());
            }
        }
    }




    public ItemStack getItemStack() {
        return itemStack;
    }
}
