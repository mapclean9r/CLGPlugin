package clgplugin.events;

import clgplugin.Items.gui.mainmenu.Menu;
import clgplugin.events.annotations.EventAlias;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

@EventAlias(eventAlias = "onPlayerJoin")
public class TestEvents implements Listener {

    private boolean itemNotNullCheck(ItemStack item){
        return item != null && !item.getType().equals(Material.AIR);
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent playerJoined){
        Player player = playerJoined.getPlayer();
        player.sendMessage("Test Send");
        player.sendTitle(ChatColor.GREEN +"title", "under-title", 10, 70 ,20);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 50, 50);

        playerJoined.getPlayer().getInventory().setItem(8, new Menu().getItemStack());
    }

    @EventHandler
    public static void onDrop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        ItemStack stack = item.getItemStack();

        if (Objects.requireNonNull(stack.getItemMeta()).hasDisplayName() &&
                stack.getItemMeta().getDisplayName().contains("Player Menu")) {
            event.setCancelled(true);
        }
    }

    @EventHandler()
    public void onInventoryItemClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if (!itemNotNullCheck(item)){
            return;
        }

        if (Objects.requireNonNull(item.getItemMeta()).hasDisplayName() && item.getItemMeta().getDisplayName().contains("Player Menu")) {
            e.setCancelled(true);
        }
    }

    @EventHandler()
    public void onMoveItem(InventoryMoveItemEvent e) {
        ItemStack item = e.getItem();

        if (Objects.requireNonNull(item.getItemMeta()).hasDisplayName() && item.getItemMeta().getDisplayName().contains("Player Menu")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreative(InventoryCreativeEvent event) {
        ItemStack item = event.getCursor();

        if (!itemNotNullCheck(item)){
            return;
        }

    }



}
