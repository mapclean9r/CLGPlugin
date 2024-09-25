package clgplugin.events;

import clgplugin.events.annotations.EventAlias;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@EventAlias(eventAlias = "onPlayerJoin")
public class TestEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent playerJoined){
        Player player = playerJoined.getPlayer();
        player.sendMessage("Test Send");
        player.sendTitle(ChatColor.GREEN +"title", "under-title", 10, 70 ,20);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 50, 50);
    }



}
