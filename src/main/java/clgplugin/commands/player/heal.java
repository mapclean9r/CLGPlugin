package clgplugin.commands.player;

import clgplugin.commands.ModCommand;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@CommandAlias(alias = "heal")
public class heal extends ModCommand {
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §e" + s + " Command Worked");

        player.setFoodLevel(20);
        player.setSaturation(20.0f);
        player.sendMessage("§aYou have been fed!");
    }
}
