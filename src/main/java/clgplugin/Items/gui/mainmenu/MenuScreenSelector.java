package clgplugin.Items.gui.mainmenu;

import clgplugin.Items.gui.slayermenu.SlayerScreen;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuScreenSelector implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }

        if (e.getClickedInventory().getHolder() instanceof MainScreen) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) { return; }

            //skills, redirect to correct display
            if (e.getCurrentItem().getType() == new MenuItemSkill().getItemStack().getType()) {
                player.sendMessage(ChatColor.RED + "Coming soon...");
            }

            if (e.getCurrentItem().getType() == new MenuItemExit().getItemStack().getType()){
                player.closeInventory();
            }

            if (e.getCurrentItem().getType() == new MenuItemSlayer().getItemStack().getType()) {
                SlayerScreen gui = new SlayerScreen();
                player.closeInventory();
                player.openInventory(gui.getInventory());
            }

        }
    }
}
