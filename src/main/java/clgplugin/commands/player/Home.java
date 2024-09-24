package clgplugin.commands.player;

import clgplugin.commands.ModCommand;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(alias = "home")
public class Home extends ModCommand {
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §e" + s + " Command Worked");
        Location n = player.getBedSpawnLocation();
        assert n != null;
        player.teleport(n);
    }
}