package clgplugin.commands;

import clgplugin.CLGPlugin;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@CommandAlias(alias = "spawnrev")
public class spawnRev extends ModCommand{
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;

        Location location = player.getLocation();
        CLGPlugin.getPlugin().spawnCustomBoss(location);

    }
}