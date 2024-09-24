package clgplugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCommand {

    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §eTest3 Command Worked");
    }
}
