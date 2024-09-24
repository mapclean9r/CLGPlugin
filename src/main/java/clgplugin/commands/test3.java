package clgplugin.commands;


import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(alias = "test3")
public class test3 extends ModCommand{
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §e"+s+" Command Worked");
    }
}