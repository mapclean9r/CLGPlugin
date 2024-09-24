package clgplugin.commands;

import clgplugin.CLGPlugin;
import clgplugin.Items.ItemManager;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(alias = "kit")
public class CommandGiveItem extends ModCommand{
    public void execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§e§l(!) §egiveitem Command Worked");
        player.getInventory().addItem(ItemManager.getMenuItem());
        player.getInventory().addItem(ItemManager.getYetiSwordItem());
    }
}
