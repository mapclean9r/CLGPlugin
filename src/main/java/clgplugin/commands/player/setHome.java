package clgplugin.commands.player;

import clgplugin.commands.ModCommand;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(alias = "sethome")
public class setHome extends ModCommand {
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §e" + s + " new spawnpoint created!");
        player.sendMessage(player.getLocation() + " new spawnpoint created!");

    }
}
