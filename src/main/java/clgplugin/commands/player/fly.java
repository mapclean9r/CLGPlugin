package clgplugin.commands.player;

import clgplugin.commands.ModCommand;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(alias = "fly")
public class fly extends ModCommand {
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §e" + s + " Command Worked");
        player.setAllowFlight(true);
        if (player.isFlying()){
            player.setFlying(false);
            player.setAllowFlight(false);
            return;
        }
        player.setFlying(true);
        try {
            player.setFlySpeed(Math.min(player.getFlySpeed() * 3, 1.0f));
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cFly speed adjustment failed. Speed value out of bounds.");
        }
    }
}

