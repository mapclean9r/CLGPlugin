package clgplugin.Items.gui.slayermenu;

import clgplugin.Items.gui.mainmenu.MenuItemExit;
import clgplugin.events.annotations.EventAlias;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@EventAlias(eventAlias = "SlayerScreenSelector")
public class SlayerScreenSelector implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }

        if (e.getClickedInventory().getHolder() instanceof SlayerScreen) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) { return; }

            //skills, redirect to correct display

            if (e.getCurrentItem().getType() == new MenuItemExit().getItemStack().getType()){
                player.closeInventory();
            }

        }
    }

}
